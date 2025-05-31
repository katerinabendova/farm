package main;

import java.util.HashMap;

public class Inventory {
    public HashMap<String, Integer> items = new HashMap<>();

    public void addItem(String name) {
        items.put(name, items.getOrDefault(name, 0) + 1);
    }

    /**
     * removes a specified amount of an item from the inventory
     * @param itemName the name of the item to remove
     * @param amount the quantity of the item to remove
     */
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

    /**
     * checks if the inventory contains at least a specified amount of an item
     * @param itemName the name of the item to check
     * @param amount the minimum quantity required
     * @return true if the inventory has the item in the required amount, false otherwise
     */
    public boolean hasItem(String itemName, int amount) {
        if (items.containsKey(itemName)) {
            return items.get(itemName) >= amount;
        }
        return false;
    }

}
