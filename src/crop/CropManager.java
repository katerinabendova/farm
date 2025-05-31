package crop;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CropManager {
    GamePanel gp;
    BufferedImage cropImage;
    public ArrayList<Crop> crops = new ArrayList<>();

    /**
     * constructs a CropManager and initializes all crops based on the game map
     * loads the crop image and scans the game world tiles to place cropsat all tiles marked with the tile number 5.
     * @param gp the GamePanel instance containing game settings and resources
     */
    public CropManager(GamePanel gp) {
        this.gp = gp;

        try {
            cropImage = ImageIO.read(getClass().getResourceAsStream("/objects/grain.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                if (gp.tileM.mapTileNum[col][row] == 5) {
                    int worldX = col * gp.tileSize;
                    int worldY = row * gp.tileSize;
                    crops.add(new Crop(worldX, worldY));
                }
            }
        }
    }

    /**
     * updates the state of all crops
     */
    public void update() {
        for (Crop crop : crops) {
            if (crop.isReadyToRespawn()) {
                crop.isHarvested = false;
                crop.growthStage = 0;
            } else if (!crop.isHarvested) {
                crop.grow();
            }
        }
    }

    /**
     * draws all unharvested crops on the screen
     * @param g2 the Graphics2D context used for drawing
     */
    public void draw(Graphics2D g2) {
        for (Crop crop : crops) {
            if (!crop.isHarvested) {
                int screenX = crop.worldX - gp.player.worldX + gp.player.screenX;
                int screenY = crop.worldY - gp.player.worldY + gp.player.screenY;
                g2.drawImage(cropImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }
    }

    /**
     * attempts to harvest a crop at the specified world coordinates
     * @param worldX the X coordinate in the world to check for a crop
     * @param worldY the Y coordinate in the world to check for a crop
     */
    public void tryHarvestCrop(int worldX, int worldY) {
        for (Crop crop : crops) {
            if (!crop.isHarvested &&
                    worldX >= crop.worldX && worldX < crop.worldX + gp.tileSize &&
                    worldY >= crop.worldY && worldY < crop.worldY + gp.tileSize) {

                if (crop.isGrown()) {
                    crop.harvest();
                    gp.inventory.addItem("grain");

                    System.out.println("Crop harvested at: " + crop.worldX + ", " + crop.worldY);
                } else {
                    System.out.println("Crop not ready yet!");
                }
                return;
            }
        }
        System.out.println("No crop found at this position!");
    }
}

