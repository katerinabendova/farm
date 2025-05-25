package main;

import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;
  //  Font arial_40;
    Font imact_80;

    public UI(GamePanel gp) {
        this.gp = gp;
        //arial_40 = new Font("Arial", Font.PLAIN, 40);
        imact_80 = new Font("IMPACT", Font.PLAIN, 80);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(imact_80);
        g2.setColor(Color.magenta);

        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
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
