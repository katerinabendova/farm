package object;

import entity.Entity;
import main.GamePanel;

public class Grain extends Entity {

    public Grain(GamePanel gp) {
        super(gp);
        name = "grain";
        down1 = setup("/objects/grain");
        collision = true;

    }
}
