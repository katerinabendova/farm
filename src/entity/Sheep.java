package entity;

import main.GamePanel;

import java.util.Random;

public class Sheep extends Animal{
    public Sheep(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        entityHeight = gp.tileSize;
        entityWidth = gp.tileSize;
        this.hungerInterval = 60000;
        this.grainNeededPerLife = 2;
        this.maxLife = 6;
        this.life = maxLife;


        getImage();

    }

    public void getImage() {

        up1 = setup("/animals/sheep_back1");
        up2 = setup("/animals/sheep_back2");
        down1 = setup("/animals/sheep_front1");
        down2 = setup("/animals/sheep_front2");
        left1 = setup("/animals/sheep_side1");
        left2 = setup("/animals/sheep_side2");
        right1 = setup("/animals/sheep_side3");
        right2 = setup("/animals/sheep_side4");

    }

    /**
     * generates a random number between 1 and 100 to select a new direction
     */
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random rd = new Random();
            int i = rd.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }
}
