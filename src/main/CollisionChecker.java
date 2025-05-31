package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    /**
     * checks for collision between the given entity and the tiles in the game world
     * based on the entity's current direction and position
     * @param entity the entity whose tile collision is to be checked
     */
    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                if (entityTopRow >= 0 && entityTopRow < gp.maxWorldRow &&
                        entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol &&
                        entityRightCol >= 0 && entityRightCol < gp.maxWorldCol) {

                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                        entity.collisionOn = true;
                    }
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                if (entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow &&
                        entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol &&
                        entityRightCol >= 0 && entityRightCol < gp.maxWorldCol) {

                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                        entity.collisionOn = true;
                    }
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                if (entityLeftCol >= 0 && entityLeftCol < gp.maxWorldCol &&
                        entityTopRow >= 0 && entityTopRow < gp.maxWorldRow &&
                        entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow) {

                    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                        entity.collisionOn = true;
                    }
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                if (entityRightCol >= 0 && entityRightCol < gp.maxWorldCol &&
                        entityTopRow >= 0 && entityTopRow < gp.maxWorldRow &&
                        entityBottomRow >= 0 && entityBottomRow < gp.maxWorldRow) {

                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                        entity.collisionOn = true;
                    }
                }
                break;
        }
    }


    /**
     * checks for a collision between the given entity and objects in the game
     * it determines if the entity will collide with any object in the direction it is moving,
     * based on its speed and solid area
     * @param entity the entity whose collision is being checked
     * @param player a boolean indicating if the entity is the player
     * @return the index of the object collided with, or 999 if no collision occurs
     */
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true){
                                entity.collisionOn = true;
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true){
                                entity.collisionOn = true;
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true){
                                entity.collisionOn = true;
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true){
                                entity.collisionOn = true;
                                index = i;
                            }
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    /**
     * checks for a collision between the given entity and other target entities
     * it calculates whether the moving entity's collision area intersects with any target entity's collision area,
     * considering the entity's direction and speed
     * @param entity the entity for which collision is being checked
     * @param target an array of target entities to check collisions against
     * @return the index of the target entity collided with, or 999 if no collision occurs
     */
    public int checkEntity(Entity entity, Entity[] target){

        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;

                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;

                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;

                                index = i;

                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    /**
     * checks if the given entity collides with the player based on their solid areas
     * it adjusts the entity's collision area according to its direction and speed,
     * then tests for intersection with the player's collision area
     * if a collision is detected, it sets the entity's collision flag to true
     * @param entity the entity to check collision against the player
     */
    public void checkPlayer(Entity entity){

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
                gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;

                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;

                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionOn = true;
                        }
                        break;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }
}