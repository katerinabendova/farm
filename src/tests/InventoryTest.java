package tests;

import main.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    Inventory inventory;

    @BeforeEach
    void init() {
        inventory = new Inventory();
    }

    @Test
    void testAddItem() {
        inventory.addItem("apple");
        assertTrue(inventory.items.containsKey("apple"));
        assertEquals(1, inventory.items.get("apple"));

        inventory.addItem("apple");
        assertEquals(2, inventory.items.get("apple"));
    }

    @Test
    void testHasItem() {
        inventory.addItem("wood");
        assertTrue(inventory.hasItem("wood", 1));
        assertFalse(inventory.hasItem("wood", 5));
        assertFalse(inventory.hasItem("stone", 1));
    }

    @Test
    void testRemoveItem() {
        inventory.addItem("fish");
        inventory.addItem("fish");
        assertEquals(2, inventory.items.get("fish"));

        inventory.removeItem("fish", 1);
        assertEquals(1, inventory.items.get("fish"));

        inventory.removeItem("fish", 1);
        assertFalse(inventory.items.containsKey("fish"));

        inventory.removeItem("fish", 1);
        assertFalse(inventory.items.containsKey("fish"));
    }

    @Test
    void testRemoveMoreThanExists() {
        inventory.addItem("carrot");
        assertEquals(1, inventory.items.get("carrot"));

        inventory.removeItem("carrot", 3);
        assertFalse(inventory.items.containsKey("carrot"));
    }

    @Test
    void testRemoveItemNotInInventory() {
        inventory.removeItem("gold", 1);
        assertFalse(inventory.items.containsKey("gold"));
    }
}
