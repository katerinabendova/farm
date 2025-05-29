package main;

import entity.Entity;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle(23, 23, 16, 16

        );
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(10, 11, "any")) {
            teleportToMap("stable.txt", 5 * gp.tileSize, 7 * gp.tileSize, gp.playState, 20, 12, "down", 5, 4);
        }
        if (hit(21, 45, "any")) {
            teleportToMap("barn.txt", 5 * gp.tileSize, 7 * gp.tileSize, gp.playState, 20, 12, "down", 5, 4);
        }

        if (hit(3, 4, "any")) {
            teleportToMap("world1.txt", 10 * gp.tileSize, 12 * gp.tileSize, gp.playState, gp.maxWorldCol, gp.maxWorldRow, "down", 2, 2);
        }
    }

    public void teleportToMap(String mapFileName, int playerX, int playerY, int gameState, int newMaxCol, int newMaxRow, String playerDirection, int playerStartCol, int playerStartRow) {
        gp.loadMapData(mapFileName);
        gp.maxWorldCol = newMaxCol;
        gp.maxWorldRow = newMaxRow;

        gp.tileM.mapTileNum = new int[newMaxCol][newMaxRow];

        gp.tileM.loadMap("/maps/" + mapFileName);

        gp.player.worldX = playerStartCol * gp.tileSize;
        gp.player.worldY = playerStartRow * gp.tileSize;
        gp.player.direction = playerDirection;

        gp.gameState = gameState;

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

    public void damagePit(int gameState) {
        gp.gameState = gameState;
    }
}
