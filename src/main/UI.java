package main;

import entity.Entity;
import object.Life;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font imact_100;
    BufferedImage lifeFull, lifeHalf, lifeBlank;
    public int commandNum = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        imact_100 = new Font("IMPACT", Font.PLAIN, 100);

        SuperObject life = new Life(gp);
        lifeFull = life.image;
        lifeHalf = life.image2;
        lifeBlank = life.image3;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(imact_100);
        g2.setColor(new Color(169,120,196));
/*
        if (gp.gameState == gp.titleState){
            drawAnimalLife();
           drawTitleScreen();
        }
        if (gp.gameState == gp.titleState){
            drawAnimalLife();
        }
        if (gp.gameState == gp.pauseState){
            drawAnimalLife();
            drawPauseScreen();
        }

 */
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        else if (gp.gameState == gp.playState || gp.gameState == gp.pauseState){
            drawAnimalLife();
            if (gp.gameState == gp.pauseState) {
                drawPauseScreen();
            }
        }
    }

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
    public void drawTitleScreen(){
        g2.setColor(new Color(185,216,149));
        g2.fillRect(0, 0, gp.screenWidht, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 85));
        String text = "FARMING SIMULATOR";
        int x = getXForCenteredText(text);
        int y = gp.titleSize*3;

        g2.setColor(Color.white);
        g2.drawString(text, x+3, y+3);

        g2.setColor(new Color(169,120,196));
        g2.drawString(text, x, y);

        x = gp.screenWidht/2 - (gp.titleSize*2)/2;
        y += gp.titleSize*2;
        g2.drawImage(gp.player.basic, x, y, gp.titleSize*2, gp.titleSize*2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.titleSize*3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x - gp.titleSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);
        if (commandNum == 1){
            g2.drawString(">", x - gp.titleSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);
        if (commandNum == 2){
            g2.drawString(">", x - gp.titleSize, y);
        }
    }
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidht/2 - length/2;
        return x;
    }
}
