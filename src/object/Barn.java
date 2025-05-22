package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Barn extends  SuperObject{

    public Barn(GamePanel gp) {
        name = "Barn";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/barn.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
