package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Life extends SuperObject{
    GamePanel gp;

    public Life(GamePanel gp) {
        this.gp = gp;
        name = "life";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            uTool.scaleImage(image, gp.titleSize, gp.titleSize);
            uTool.scaleImage(image2, gp.titleSize, gp.titleSize);
            uTool.scaleImage(image3, gp.titleSize, gp.titleSize);

        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
