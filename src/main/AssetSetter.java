package main;

import entity.*;
import object.Barn;
import object.Stable;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjectWorld1() {
        gp.obj = new Entity[10];

        gp.obj[0] = new Barn(gp);
        gp.obj[0].worldX = 8 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;

        gp.obj[1] = new Stable(gp);
        gp.obj[1].worldX = 19 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
    }

    public void setAnimalWorld1() {
        gp.animals = new Entity[10];

        gp.animals[0] = new Chicken(gp);
        gp.animals[0].worldX = gp.tileSize * 21;
        gp.animals[0].worldY = gp.tileSize * 21;

        gp.animals[1] = new Cow(gp);
        gp.animals[1].worldX = gp.tileSize * 4;
        gp.animals[1].worldY = gp.tileSize * 4;

        gp.animals[2] = new Dog(gp);
        gp.animals[2].worldX = gp.tileSize * 37;
        gp.animals[2].worldY = gp.tileSize * 3;

        gp.animals[3] = new Sheep(gp);
        gp.animals[3].worldX = gp.tileSize * 25;
        gp.animals[3].worldY = gp.tileSize * 35;
    }

    public void setAnimalStable() {
        gp.animals = new Entity[10];
    }

    public void setObjectStable() {
        gp.obj = new Entity[10];
    }
}
