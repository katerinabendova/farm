package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Star extends SuperObject {

    GamePanel gp;

    public Star(GamePanel gp) {
        name = "Star";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/star.png"));

            uTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
