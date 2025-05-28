package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Milk extends Entity {

    public Milk(GamePanel gp) {
        super(gp);
        name = "milk";
        down1 = setup("/objects/milk");
        collision = true;
    }
}
