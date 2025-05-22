package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Stable extends SuperObject{

    public Stable(GamePanel gp) {
        name = "Stable";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/stable.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
