package html.to.json.pojo;

import java.util.ArrayList;
import java.util.List;

public class BuildStack {

    public String name = "Build Stack";
    public List<Item> items = new ArrayList<>();

    public static class Item {
        public String tech;
        public String use;
        public String reason;
        public String lifeCycle;
    }
}
