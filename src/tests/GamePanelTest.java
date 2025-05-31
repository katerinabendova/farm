package tests;

import main.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GamePanelTest {
    GamePanel gp;

    @BeforeEach
    void init() {
        gp = new GamePanel();
    }

    @Test
    void testSetupGame() {
        gp.setupGame();
        assertEquals(gp.titleState, gp.gameState, "After setup, gameState should be set to titleState");
    }

    @Test
    void testLoadMapData_world1() {
        gp.loadMapData("world1.txt");

        assertNotNull(gp.obj, "Objects should not be null");
        assertNotNull(gp.animals, "Animals should not be null");

        assertEquals(gp.tileM.maxWorldCol, gp.maxWorldCol);
        assertEquals(gp.tileM.maxWorldRow, gp.maxWorldRow);
    }

    @Test
    void testGameStates() {
        gp.setupGame();

        assertEquals(0, gp.titleState);
        assertEquals(1, gp.playState);
        assertEquals(2, gp.pauseState);
        assertEquals(3, gp.gameOverState);
    }

    @Test
    void testStartGameThread() {
        gp.startGameThread();
        assertNotNull(gp.gameTreader, "The game thread should be initialized");
        assertTrue(gp.gameTreader.isAlive(), "The game thread should be running");
    }
}