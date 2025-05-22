package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Nettle extends SuperObject{

    public Nettle() {
        name = "Nettle";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/nettle.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
