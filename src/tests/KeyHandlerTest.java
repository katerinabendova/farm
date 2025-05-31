package tests;

import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class KeyHandlerTest {

    GamePanel gp;
    KeyHandler keyHandler;

    @BeforeEach
    void init() {
        gp = new GamePanel();
        keyHandler = new KeyHandler(gp);
    }

    @Test
    void testMovementKeyPressed() {
        gp.gameState = gp.playState;

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'));
        assertTrue(keyHandler.upPressed);

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S'));
        assertTrue(keyHandler.downPressed);

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A'));
        assertTrue(keyHandler.leftPressed);

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D'));
        assertTrue(keyHandler.rightPressed);
    }

    @Test
    void testMovementKeyReleased() {
        keyHandler.upPressed = true;
        keyHandler.downPressed = true;
        keyHandler.leftPressed = true;
        keyHandler.rightPressed = true;

        keyHandler.keyReleased(new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'));
        assertFalse(keyHandler.upPressed);

        keyHandler.keyReleased(new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S'));
        assertFalse(keyHandler.downPressed);

        keyHandler.keyReleased(new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A'));
        assertFalse(keyHandler.leftPressed);

        keyHandler.keyReleased(new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D'));
        assertFalse(keyHandler.rightPressed);
    }

    @Test
    void testTPressed() {
        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_T, 'T'));
        assertTrue(keyHandler.tPressed);

        keyHandler.keyReleased(new KeyEvent(gp, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_T, 'T'));
        assertFalse(keyHandler.tPressed);
    }

    @Test
    void testPauseToggle() {
        gp.gameState = gp.playState;
        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P'));
        assertEquals(gp.pauseState, gp.gameState);

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P'));
        assertEquals(gp.playState, gp.gameState);
    }

    @Test
    void testTitleMenuNavigation() {
        gp.gameState = gp.titleState;
        gp.ui.commandNum = 0;

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S'));
        assertEquals(1, gp.ui.commandNum);

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'));
        assertEquals(0, gp.ui.commandNum);

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'));
        assertEquals(2, gp.ui.commandNum);
    }

    @Test
    void testStartGameFromTitle() {
        gp.gameState = gp.titleState;
        gp.ui.commandNum = 0;

        keyHandler.keyPressed(new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER, '\n'));
        assertEquals(gp.playState, gp.gameState);
    }
}
