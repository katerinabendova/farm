package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Milk extends SuperObject{

    public Milk() {
        name = "Milk";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/milk.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
