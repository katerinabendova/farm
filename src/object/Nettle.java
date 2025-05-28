package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Nettle extends Entity {


    public Nettle(GamePanel gp) {
        super(gp);
        name = "nettle";
        down1 = setup("/objects/nettle");
        collision = true;
    }
}
