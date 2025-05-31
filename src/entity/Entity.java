package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, basic;
    public BufferedImage image,image2, image3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;
    public String name;
    public int entityWidth;
    public int entityHeight;
    public boolean collision = false;
    public int actionLockCounter = 0;

    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidAreaDefaultY = solidArea.y;
        this.direction = "down";
    }

    public void setAction() {
    }

    /**
     * updates the entity's state each frame
     */
    public void update() {
        setAction();
        collisionOn = false;

        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    this.worldY -= speed;
                    break;
                case "down":
                    this.worldY += speed;
                    break;
                case "left":
                    this.worldX -= speed;
                    break;
                case "right":
                    this.worldX += speed;
                    break;

            }
        }
        spriteCounter++;

        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    /**
     * Draws the entity on the screen relative to the player's position
     * @param g2 the Graphics2D object used for drawing the entity image
     */
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction){
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1){
                        image = right1;
                    }
                    if (spriteNum == 2){
                        image = right2;
                    }
                    break;
            }

            if (image != null) {
                g2.drawImage(image, screenX, screenY, entityWidth, entityHeight, null);
            }
        }
    }

    /**
     * loads an image from the specified path, scales it to the game's tile size, and returns it
     * @param imagePath the relative path to the image resource (without the ".png" extension)
     * @return the loaded and scaled BufferedImage, or null if loading fails
     */
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    /**
     * loads an image from the specified path, scales it by the given scale factor based on the game's tile size
     * @param imagePath the relative path to the image resource (without the ".png" extension)
     * @param scaleFactor the factor by which to scale the image relative to the tile size
     * @return the loaded and scaled BufferedImage, or null if loading fails
     */
    public BufferedImage setupBig(String imagePath, int scaleFactor){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.tileSize * scaleFactor, gp.tileSize * scaleFactor);
        } catch (IOException e){
            e.printStackTrace();
        }

        return image;
    }

    public boolean isHungry() {
        return this.life < this.maxLife;
    }


}