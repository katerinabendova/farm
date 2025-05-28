package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle(23, 23, 8, 8);
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(10, 10, "any")) {
            teleportToMap("/maps/stable.txt", 5 * gp.tileSize, 7 * gp.tileSize, gp.playState);
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        int playerSolidAreaX = gp.player.worldX + gp.player.solidArea.x;
        int playerSolidAreaY = gp.player.worldY + gp.player.solidArea.y;

        int eventRectX = eventCol * gp.tileSize + eventRect.x;
        int eventRectY = eventRow * gp.tileSize + eventRect.y;

        Rectangle playerRect = new Rectangle(playerSolidAreaX, playerSolidAreaY, gp.player.solidArea.width, gp.player.solidArea.height);
        Rectangle eventRectangle = new Rectangle(eventRectX, eventRectY, eventRect.width, eventRect.height);

        if (playerRect.intersects(eventRectangle)) {
            if (gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
                hit = true;
            }
        }

        return hit;
    }

    public void teleportToMap(String mapFilePath, int newPlayerX, int newPlayerY, int gameState) {
        gp.tileM.loadMap(mapFilePath);

        gp.player.worldX = newPlayerX;
        gp.player.worldY = newPlayerY;

        gp.gameState = gameState;

        // gp.requestFocusInWindow();
    }

    public void damagePit(int gameState) {
        gp.gameState = gameState;
    }
}
