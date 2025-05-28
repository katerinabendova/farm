package object;

import entity.Entity;
import main.GamePanel;

public class Life extends Entity {

    public Life(GamePanel gp) {
        super(gp);
        name = "life";

        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");
    }
}
