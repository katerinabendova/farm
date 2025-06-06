package main;

import entity.Entity;
import object.Life;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font imact_100;
    BufferedImage lifeFull, lifeHalf, lifeBlank;
    public int commandNum = 0;
    private String warningMessage = "";



    public UI(GamePanel gp) {
        this.gp = gp;
        imact_100 = new Font("IMPACT", Font.PLAIN, 100);
    }

    public void loadLifeImages() {
        Entity life = new Life(gp);
        lifeFull = life.image;
        lifeHalf = life.image2;
        lifeBlank = life.image3;
    }

    /**
     * renders UI elements based on the current game state
     * @param g2 he Graphics2D object used for drawing UI components
     */
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(imact_100);
        g2.setColor(new Color(169,120,196));
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        else if (gp.gameState == gp.playState || gp.gameState == gp.pauseState){
            drawAnimalLife();
            updateWarningMessage();
            drawInventory();
        }
            if (gp.gameState == gp.pauseState) {
                drawPauseScreen();
            }
        if (gp.gameState == gp.gameOverState) {
            drwGameOverScreen();
        }
    }


    /**
     * draws the health (life) indicators above all active animals on the screen
     */
    public void drawAnimalLife(){
        for (int i = 0; i < gp.animals.length; i++) {
            if (gp.animals[i] != null){
                Entity e = gp.animals[i];

                int screenX = e.worldX - gp.player.worldX + gp.player.screenX;
                int screenY = e.worldY - gp.player.worldY + gp.player.screenY - 10;

                int lifeX = screenX;
                int lifeY = screenY;

                for (int j = 0; j < e.maxLife / 2; j++) {
                    g2.drawImage(lifeBlank, lifeX, lifeY, 16, 16, null);
                    lifeX += 16;
                }
                lifeX = screenX;

                int iLife = e.life;
                while (iLife > 0){
                    if (iLife >= 2){
                        g2.drawImage(lifeFull, lifeX, lifeY, 16, 16, null);
                        iLife -= 2;
                    } else {
                        g2.drawImage(lifeHalf, lifeX, lifeY, 16, 16, null);
                        iLife -= 1;
                    }
                    lifeX += 16;
                }
            }

        }
    }

    /**
     *  draws the title screen, including the game title, player sprite, and menu options
     */
    public void drawTitleScreen(){
        g2.setColor(new Color(185,216,149));
        g2.fillRect(0, 0, gp.screenWidht, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 85));
        String text = "FARMING SIMULATOR";
        int x = getXForCenteredText(text);
        int y = gp.tileSize *3;

        g2.setColor(Color.white);
        g2.drawString(text, x+3, y+3);

        g2.setColor(new Color(169,120,196));
        g2.drawString(text, x, y);

        x = gp.screenWidht/2 - (gp.tileSize *2)/2;
        y += gp.tileSize *2;
        g2.drawImage(gp.player.basic, x, y, gp.tileSize *2, gp.tileSize *2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize *3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    /**
     * draws the pause screen with a centered "PAUSED" message
     */
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.setFont(imact_100);
        g2.setColor(new Color(169,120,196));
        g2.drawString(text, x, y);
    }

    /**
     * draws the game over screen with a centered "GAME OVER" message
     */
    public void drwGameOverScreen(){
        g2.setColor(new Color(185,216,149));
        g2.fillRect(0, 0, gp.screenWidht, gp.screenHeight);

        g2.setFont(new Font("IMPACT", Font.BOLD, 100));
        g2.setColor(new Color(169,120,196));
        String text = "GAME OVER";

        int x = getXForCenteredText(text);
        int y = gp.tileSize *3;
        g2.drawString(text, x, y);
    }

    /**
     * calculates the x-coordinate to center a given text string horizontally on the screen
     * @param text the text string to be centered
     * @return the x-coordinate where the text should start for horizontal centering
     */
    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidht/2 - length/2;
        return x;
    }

    /**
     * draws the player's inventory and warning messages on the screen
     */
    public void drawInventory() {
        g2.setColor(Color.white);
        g2.setFont(new Font("IMPACT", Font.PLAIN, 20));

        int x = 20;
        int y = 40;

        g2.drawString("INVENTORY:", x, y);
        y += 30;

        if (gp.inventory.items.isEmpty()) {
            g2.drawString("- empty -", x, y);
        } else {
            for (String itemName : gp.inventory.items.keySet()) {
                int count = gp.inventory.items.get(itemName);
                g2.drawString(itemName + ": " + count, x, y);
                y += 25;
            }
        }
        if (!warningMessage.isEmpty()) {
            y += 20;
            g2.setColor(Color.RED);
            g2.drawString(warningMessage, x, y);
        }
    }

    /**
     * updates the warning message based on the hunger status of animals
     */
    public void updateWarningMessage() {
        boolean animalHungry = false;

        for (int i = 0; i < gp.animals.length; i++) {
            if (gp.animals[i] != null) {
                if (gp.animals[i].isHungry()) {
                    animalHungry = true;
                    break;
                }
            }
        }

        if (animalHungry) {
            warningMessage = "WARNING: Some animals are hungry!";
        } else {
            warningMessage = "";
        }
    }


}
