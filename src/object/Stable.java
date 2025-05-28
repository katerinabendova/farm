package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Stable extends Entity {

    public Stable(GamePanel gp) {
        super(gp);
        name = "stable";

        entityWidth = gp.tileSize * 4;
        entityHeight = gp.tileSize * 4;

        down1 = setup("/objects/stable");

        collision = true;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = entityWidth;
        solidArea.height = entityHeight;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
