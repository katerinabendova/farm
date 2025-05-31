package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle(23, 23, 16, 16);
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    /**
     * checks if the player has triggered any predefined map events based on their position and direction
     */
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

    /**
     * teleports the player to a new map with specified starting position and state
     * @param mapFileName the filename of the map to load
     * @param playerX the target player X coordinate
     * @param playerY the target player Y coordinate
     * @param gameState the new game state to set after teleporting
     * @param newMaxCol the new maximum number of columns in the map
     * @param newMaxRow the new maximum number of rows in the map
     * @param playerDirection the direction the player should face after teleporting
     * @param playerStartCol the column index where the player starts on the new map
     * @param playerStartRow the row index where the player starts on the new map
     */
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

    /**
     * checks if the player has collided with an event area in a specific direction
     * @param eventCol the column index of the event tile
     * @param eventRow the row index of the event tile
     * @param reqDirection reqDirection the required player direction to trigger the event
     * @return true if the player's collision area intersects the event area and the direction matches; false otherwise
     */
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

}
