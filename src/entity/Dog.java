package entity;

import main.GamePanel;

import java.util.Random;

public class Dog extends Entity{

    public Dog(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        entityWidth = gp.tileSize;
        entityHeight = gp.tileSize;
        // we can set solid area here if we want
        maxLife = 6;
        life = maxLife;

        getImage();
    }
    public void getImage(){

        up1 = setup("/animals/dog_back1");
        up2 = setup("/animals/dog_back2");
        down1 = setup("/animals/dog_front1");
        down2 = setup("/animals/dog_front2");
        left1 = setup("/animals/dog_side1");
        left2 = setup("/animals/dog_side2");
        right1 = setup("/animals/dog_side3");
        right2 = setup("/animals/dog_side4");

    }

    public void setAction(){
        actionLockCounter ++;

        if (actionLockCounter == 120){
            Random rd = new Random();
            int i = rd.nextInt(100)+1;

            if (i <= 25){
                direction = "up";
            }
            if (i > 25 && i <= 50){
                direction = "down";
            }
            if (i > 50 && i <= 75){
                direction = "left";
            }
            if (i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }
}
