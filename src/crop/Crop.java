package crop;

import java.awt.image.BufferedImage;

public class Crop{
    public int worldX, worldY;
    public boolean isHarvested = false;
    public long harvestTime;
    public boolean harvested;
    public int growthStage = 0;
    public int maxGrowthStage = 5;


    public Crop(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public boolean isReadyToRespawn() {
        return isHarvested && (System.currentTimeMillis() - harvestTime > 120_000); // 2 min
    }
    public boolean isGrown() {
        return growthStage >= maxGrowthStage && !isHarvested;
    }
    public void grow() {
        if (growthStage < maxGrowthStage) {
            growthStage++;
        }
    }
    public void harvest() {
        if (isGrown()) {
            isHarvested = true;
            harvestTime = System.currentTimeMillis();
            growthStage = 0;
        }
    }



}
