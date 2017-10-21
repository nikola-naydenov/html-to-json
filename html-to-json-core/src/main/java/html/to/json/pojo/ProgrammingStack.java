package html.to.json.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProgrammingStack {

    public String name = "Programming Stack";
    public List<Item> items = new ArrayList<>();

    public static class Item {
        public String tech;
        public String reason;
        public String lifecycle;
    }
}
