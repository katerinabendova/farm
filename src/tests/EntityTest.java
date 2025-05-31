package tests;

import entity.Entity;
import main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    private GamePanel gp;
    private Entity entity;

    @BeforeEach
    public void setup() {
        gp = new GamePanel();
        entity = new Entity(gp);
    }

    @Test
    public void testEntityInitialization() {
        assertEquals("down", entity.direction);
        assertNotNull(entity.solidArea);
        assertEquals(new Rectangle(0, 0, 48, 48), entity.solidArea);
    }

    @Test
    public void testIsHungryReturnsTrueWhenLifeLessThanMax() {
        entity.maxLife = 10;
        entity.life = 5;

        assertTrue(entity.isHungry(), "Entity should be hungry when life < maxLife");
    }

    @Test
    public void testIsHungryReturnsFalseWhenLifeEqualsMax() {
        entity.maxLife = 10;
        entity.life = 10;

        assertFalse(entity.isHungry(), "Entity should not be hungry when life == maxLife");
    }

    @Test
    public void testIsHungryReturnsFalseWhenLifeMoreThanMax() {
        entity.maxLife = 10;
        entity.life = 15;

        assertFalse(entity.isHungry(), "Entity should not be hungry when life > maxLife");
    }
}
