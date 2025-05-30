package entity;

import main.GamePanel;

public class Animal extends Entity{
    protected long lastHungerTime;
    protected long hungerInterval;
    protected int grainNeededPerLife;
    protected String grainName = "grain";

    public Animal(GamePanel gp) {
        super(gp);
        lastHungerTime = System.currentTimeMillis();
    }

    public void updateHunger() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastHungerTime >= hungerInterval) {
            if (life > 0) {
                life--;

                if (life <= 2) {
                    int missingLives = maxLife - life;
                    int grainsNeeded = missingLives * grainNeededPerLife;

                    if (gp.inventory.hasItem(grainName, grainsNeeded)) {
                        gp.inventory.removeItem(grainName, grainsNeeded);
                        life += missingLives;
                        if (life > maxLife) life = maxLife;
                        System.out.println(getClass().getSimpleName() + " fed with grain, life restored to: " + life);
                    } else {
                        System.out.println("Not enough grain to feed " + getClass().getSimpleName() + "!");
                    }
                }

                if (life <= 0) {
                    System.out.println("GAME OVER - " + getClass().getSimpleName() + " died!");
                    gp.gameState = gp.gameOverState;
                }
            }
            lastHungerTime = currentTime;
        }
    }

    @Override
    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        gp.cChecker.checkTile(this);

        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
        updateHunger();
    }
    public boolean isHungry() {
        return this.life <= 2;
    }

}
