package object;

import entity.Entity;
import main.GamePanel;

public class Barn extends Entity {

    public Barn(GamePanel gp) {
        super(gp);
        name = "Barn";

        entityWidth = gp.tileSize * 4;
        entityHeight = gp.tileSize * 4;

        down1 = setupBig("/objects/barn", 4);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = entityWidth;
        solidArea.height = entityHeight;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
