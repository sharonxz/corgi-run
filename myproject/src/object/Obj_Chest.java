package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Chest extends SuperObject{

    public Obj_Chest() {
        
        name = "Chest";
        try {
            image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/objects/chest.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
