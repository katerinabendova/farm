package tests;

import main.EventHandler;
import main.GamePanel;
import entity.Player;
import main.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class EventHandlerTest {

    GamePanel gp;
    EventHandler eventHandler;

    @BeforeEach
    void init() {
        gp = new GamePanel();
        eventHandler = new EventHandler(gp);
        GamePanel gp = new GamePanel();
        KeyHandler keyH = new KeyHandler(gp);
        gp.player = new Player(gp, keyH);
        gp.player.solidArea = new Rectangle(8, 16, 32, 32);
    }

    @Test
    void hitMatchingPositionAndDirection() {
        gp.player.worldX = 10 * gp.tileSize + 5;
        gp.player.worldY = 11 * gp.tileSize + 5;
        gp.player.direction = "down";

        assertTrue(eventHandler.hit(10, 11, "down"));
        assertTrue(eventHandler.hit(10, 11, "any"));
    }

    @Test
    void hitFailsOnDirectionMismatch() {
        gp.player.worldX = 10 * gp.tileSize + 5;
        gp.player.worldY = 11 * gp.tileSize + 5;
        gp.player.direction = "up";

        assertFalse(eventHandler.hit(10, 11, "left"));
    }

    @Test
    void hitFailsWhenNotOverlapping() {
        gp.player.worldX = 0;
        gp.player.worldY = 0;
        gp.player.direction = "down";

        assertFalse(eventHandler.hit(10, 11, "any"));
    }

    @Test
    void teleportToMapSetsCorrectValues() {
        gp.tileM.mapTileNum = new int[1][1];

        eventHandler.teleportToMap("barn.txt", 100, 150, gp.playState, 30, 20, "up", 2, 3);

        assertEquals(30, gp.maxWorldCol);
        assertEquals(20, gp.maxWorldRow);
        assertEquals(2 * gp.tileSize, gp.player.worldX);
        assertEquals(3 * gp.tileSize, gp.player.worldY);
        assertEquals("up", gp.player.direction);
        assertEquals(gp.playState, gp.gameState);
    }
}
