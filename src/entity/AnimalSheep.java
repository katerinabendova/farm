package entity;

import main.GamePanel;

import java.util.Random;

public class AnimalSheep extends Entity{

    public AnimalSheep(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        // we can set solid area here if we want

        getImage();
    }
    public void getImage(){

        up1 = setup("/animals/sheep_back1");
        up2 = setup("/animals/sheep_back2");
        down1 = setup("/animals/sheep_front1");
        down2 = setup("/animals/sheep_front2");
        left1 = setup("/animals/sheep_side1");
        left2 = setup("/animals/sheep_side2");
        right1 = setup("/animals/sheep_side3");
        right2 = setup("/animals/sheep_side4");

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
