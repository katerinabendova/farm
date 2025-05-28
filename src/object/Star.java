package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Star extends Entity {

    public Star(GamePanel gp) {
        super(gp);
        name = "star";
        down1 = setup("/objects/star");
        collision = true;
    }
}
