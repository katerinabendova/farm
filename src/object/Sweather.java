package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Sweather extends Entity {

    public Sweather(GamePanel gp) {
        super(gp);
        name = "sweather";
        down1 = setup("/objects/sweather"
        );
        collision = true;
    }
}
