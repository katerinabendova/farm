package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    /**
     * scales a given image to the specified width and height
     * @param original the original BufferedImage to be scaled
     * @param width the desired width of the scaled image
     * @param height the desired height of the scaled image
     * @return a new BufferedImage scaled to the specified dimensions
     */
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original,0,0, width, height, null);

        return scaledImage;

    }
}
