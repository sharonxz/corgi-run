package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Ball extends SuperObject{

    public Obj_Ball() {
        
        name = "Ball";
        try {
            image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/objects/ball.png"));

        }catch(IOException e){
        }
    }

}
