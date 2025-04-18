package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler kh;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        screenX = gp.screenWidht / 2 - (gp.titleSize / 2);
        screenY = gp.screenHeight / 2 - (gp.titleSize / 2);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = gp.titleSize * 15; //players position on the world map
        this.worldY = gp.titleSize * 24;
        this.speed = 5;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/farmer_back1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/farmer_back2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/farmer_front1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/farmer_front2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/farmer_side3.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/farmer_side4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/farmer_side1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/farmer_side2.png")));

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if (kh.upPressed == true || kh.downPressed == true || kh.leftPressed == true || kh.rightPressed == true){

            if (this.kh.upPressed) {
                direction = "up";
                this.worldY -= this.speed;
            } else if (this.kh.downPressed) {
                direction = "down";
                this.worldY += this.speed;
            } else if (this.kh.leftPressed) {
                direction = "left";
                this.worldX -= this.speed;
            } else if (this.kh.rightPressed) {
                direction = "right";
                this.worldX += this.speed;
            }

            spriteCounter ++;

            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

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
        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
    }
}

