package entity;

import main.GamePanel;

import java.util.Random;

public class Cow extends Animal {

    public Cow(GamePanel gp) {
        super(gp);
        direction = "down";
       // spriteNum = 1;
        speed = 1;
        entityHeight = gp.tileSize;
        entityWidth = gp.tileSize;
        this.hungerInterval = 90000;
        this.grainNeededPerLife = 4;
        this.maxLife = 6;
        this.life = maxLife;

        getImage();
    }

    public void getImage() {
        up1 = setup("/animals/cow_back1");
        up2 = setup("/animals/cow_back2");
        down1 = setup("/animals/cow_front1");
        down2 = setup("/animals/cow_front2");
        left1 = setup("/animals/cow_side1");
        left2 = setup("/animals/cow_side2");
        right1 = setup("/animals/cow_side3");
        right2 = setup("/animals/cow_side4");
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random rd = new Random();
            int i = rd.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
}
