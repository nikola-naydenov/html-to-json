package html.to.json.pojo;

import java.util.ArrayList;
import java.util.List;

public class Monitoring {

    public String name = "Monitoring";
    public List<Item> items = new ArrayList<>();

    public static class Item {
        public String tech;
        public String use;
        public String reason;
    }
}
