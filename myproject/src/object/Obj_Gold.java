package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Gold extends SuperObject{

    public Obj_Gold() {
        
        name = "Gold";
        try {
            image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/objects/gold.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
