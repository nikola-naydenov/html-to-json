package html.to.json.pojo;

import java.util.ArrayList;
import java.util.List;

public class Infrastructure {

    public String name = "Infrastructure";
    public List<Item> items = new ArrayList<>();

    public static class Item {
        public String infrastructure;
        public String use;
        public String lifeCycle;
    }
}
