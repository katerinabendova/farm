package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Sweather extends SuperObject{
    public Sweather() {
        name = "Sweather";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sweather.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
