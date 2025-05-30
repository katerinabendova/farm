package main;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public HashMap<String, Integer> items = new HashMap<>();

    public void addItem(String name) {
        items.put(name, items.getOrDefault(name, 0) + 1);
    }

    public void removeItem(String name) {
        if (items.containsKey(name)) {
            int count = items.get(name);
            if (count > 1) {
                items.put(name, count - 1);
            } else {
                items.remove(name);
            }
        }
    }
}
