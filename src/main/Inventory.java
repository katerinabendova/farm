package main;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public HashMap<String, Integer> items = new HashMap<>();

    public void addItem(String name) {
        items.put(name, items.getOrDefault(name, 0) + 1);
    }

    public void removeItem(String itemName, int amount) {
        if (hasItem(itemName, amount)) {
            int currentAmount = items.get(itemName);
            int newAmount = currentAmount - amount;
            if (newAmount <= 0) {
                items.remove(itemName);
            } else {
                items.put(itemName, newAmount);
            }
        }
    }

    public boolean hasItem(String itemName, int amount) {
        if (items.containsKey(itemName)) {
            return items.get(itemName) >= amount;
        }
        return false;
    }

}
