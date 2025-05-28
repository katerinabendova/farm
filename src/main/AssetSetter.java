package main;

import entity.AnimalChicken;
import entity.AnimalCow;
import entity.AnimalDog;
import entity.AnimalSheep;
import object.Barn;
import object.Stable;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Barn(gp);
        gp.obj[0].worldX = 8 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;

        gp.obj[1] = new Stable(gp);
        gp.obj[1].worldX = 19 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

    }

    public void setAnimal(){
        gp.animals[0] = new AnimalChicken(gp);
        gp.animals[0].worldX = gp.tileSize * 21;
        gp.animals[0].worldY = gp.tileSize * 21;

        gp.animals[1] = new AnimalCow(gp);
        gp.animals[1].worldX = gp.tileSize * 4;
        gp.animals[1].worldY = gp.tileSize * 4;

        gp.animals[2] = new AnimalDog(gp);
        gp.animals[2].worldX = gp.tileSize * 37;
        gp.animals[2].worldY = gp.tileSize * 3;

        gp.animals[3] = new AnimalSheep(gp);
        gp.animals[3].worldX = gp.tileSize * 25;
        gp.animals[3].worldY = gp.tileSize * 35;
    }
}
