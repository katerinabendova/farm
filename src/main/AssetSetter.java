package main;

import entity.AnimalChicken;
import object.Barn;
import object.Stable;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new Barn(gp);
        gp.obj[0].worldX = 9 * gp.titleSize;
        gp.obj[0].worldY = 6 * gp.titleSize;

        gp.obj[1] = new Stable(gp);
        gp.obj[1].worldX = 20 * gp.titleSize;
        gp.obj[1].worldY = 43 * gp.titleSize;

    }

    public void setAnimal(){
        gp.animals[0] = new AnimalChicken(gp);
        gp.animals[0].worldX = gp.titleSize * 21;
        gp.animals[0].worldY = gp.titleSize * 21;
    }
}
