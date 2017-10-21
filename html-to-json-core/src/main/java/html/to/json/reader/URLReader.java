package html.to.json.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import html.to.json.pojo.BuildStack;
import html.to.json.pojo.Infrastructure;
import html.to.json.pojo.Monitoring;
import html.to.json.pojo.ProgrammingStack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class URLReader {

    private static final String TAG_PATTERN = "<(.+?)>";

    public static void main(String[] args) throws Exception {

        URL oracle = new URL("https://github.com/egis/handbook/blob/master/Tech-Stack.md");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));


        ProgrammingStack pStack = new ProgrammingStack();
        BuildStack bStack = new BuildStack();
        Infrastructure iStack = new Infrastructure();
        Monitoring mStack = new Monitoring();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("Programming Stack")) {

                while ((inputLine = in.readLine()) != null
                        && !inputLine.contains("Build Stack")) {
                    if (inputLine.contains("<td>")) {
                        // make a new item
                        ProgrammingStack.Item item = new ProgrammingStack.Item();
                        item.tech = clearTags(inputLine);
                        item.reason = clearTags(in.readLine());
                        item.lifecycle = clearTags(in.readLine());
                        pStack.items.add(item);
                    }
                }

                while ((inputLine = in.readLine()) != null
                        && !inputLine.contains("Infrastructure")) {
                    if (inputLine.contains("<td>")) {
                        // make a new item
                        BuildStack.Item item = new BuildStack.Item();
                        item.tech = clearTags(inputLine);
                        item.use = clearTags(in.readLine());
                        item.reason = clearTags(in.readLine());
                        item.lifeCycle = clearTags(in.readLine());
                        bStack.items.add(item);
                    }
                }

                while ((inputLine = in.readLine()) != null
                        && !inputLine.contains("Monitoring")) {
                    if (inputLine.contains("<td>")) {
                        // make a new item
                        Infrastructure.Item item = new Infrastructure.Item();
                        item.infrastructure = clearTags(inputLine);
                        item.use = clearTags(in.readLine());
                        item.lifeCycle = clearTags(in.readLine());
                        iStack.items.add(item);
                    }
                }

                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains("<td>")) {
                        // make a new item
                        Monitoring.Item item = new Monitoring.Item();
                        item.tech = clearTags(inputLine);
                        item.use = clearTags(in.readLine());
                        item.reason = clearTags(in.readLine());
                        mStack.items.add(item);
                    }
                }
            }
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        //initially I tried using the writeValue method but it closed the output stream
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pStack));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bStack));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(iStack));
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mStack));
    }

    private static String clearTags(String input) {
        return input.replaceAll(TAG_PATTERN, "");
    }
}