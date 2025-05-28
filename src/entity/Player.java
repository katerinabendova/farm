package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler kh;

    public final int screenX;
    public final int screenY;

    //public int hasStar = 0;

    public Player(GamePanel gp, KeyHandler kh) {
        super(gp);

        this.gp = gp;
        this.kh = kh;

        screenX = gp.screenWidht / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 6; //players position on the world map
        worldY = gp.tileSize * 3;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){

            up1 = setup("/player/farmer_back1");
            up2 = setup("/player/farmer_back2");
            down1 = setup("/player/farmer_front1");
            down2 = setup("/player/farmer_front2");
            left1 = setup("/player/farmer_side3");
            left2 = setup("/player/farmer_side4");
            right1 = setup("/player/farmer_side1");
            right2 = setup("/player/farmer_side2");
            basic = setup("/player/farmer_basic");
    }

    public void update() {

        if (kh.upPressed == true || kh.downPressed == true || kh.leftPressed == true || kh.rightPressed == true){

            if (this.kh.upPressed) {
                direction = "up";

            } else if (this.kh.downPressed) {
                direction = "down";

            } else if (this.kh.leftPressed) {
                direction = "left";

            } else if (this.kh.rightPressed) {
                direction = "right";

            }

            // check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // check animal collision
            int animalIndex = gp.cChecker.checkEntity(this, gp.animals);
            interactAnimal(animalIndex);

            if (collisionOn == false){
                switch (direction){
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

    public void pickUpObject(int i){
        if (i != 999){
        }
    }

    public void interactAnimal(int i){
        if (i != 999){
            System.out.println("you are hitting an animal");
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
        g2.drawImage(image, screenX, screenY, null);
    }

}

