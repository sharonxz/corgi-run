package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyManager;


public final class Player extends Entity {

    public GamePanel gp;
    public KeyManager keyM;

    public final int screenX;
    public final int screenY;

    public int goldBoneCount = 0;
    public int basicBoneCount = 0;


    public Player (GamePanel gp, KeyManager keyM){
        this.gp = gp;
        this.keyM = keyM;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
    
        solidArea = new Rectangle(0, 16, 32, 32);
        solidAreaOriginalX = solidArea.x;
        solidAreaOriginalY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    
    // Sets the Player at its initial starting point on the world map (center),
    // gives it its initial moving speed, and its starting direction 
    public void setDefaultValues(){
        worldX = gp.tileSize * 19;
        worldY = gp.tileSize * 11;
        speed = 4;
        direction = "down";
    }

    // Reads the different images for the Player and sets them to their corresponding
    // direction movement
    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/corgi/up1.png"));
            up2 = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/corgi/up2.png"));
            down1 = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/corgi/down1.png"));
            down2 = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/corgi/down2.png"));
            left1 = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/corgi/left1.png"));
            left2 = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/corgi/left2.png"));
            right1 = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/corgi/right1.png"));
            right2 = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/corgi/right2.png"));

        }catch (IOException e){
        }
    }

    // Updates the image of the Player based on the KeyManager keypressed function which looks at
    // which direction the Player wants to move in
    public void update(){

        if (keyM.upPressed == true ||keyM.downPressed == true || keyM.leftPressed == true || keyM.rightPressed == true){

        if(keyM.upPressed == true){
            direction = "up";
        }
        else if(keyM.downPressed == true){
            direction = "down";
        }
        else if(keyM.leftPressed == true){
            direction = "left";
        }
        else if(keyM.rightPressed == true){
            direction = "right";
        }

        // Check specific tile collisons
        collisionOn = false;
        gp.checker.checkTile(this);

        // Check object collisions
        int objectIndex = gp.checker.checkObject(this, true);
        pickObject(objectIndex);


        // If collision is FALSE, Player can move
        if (collisionOn == false){

            if (direction.equals("up")) {
                worldY -= speed;
            }
            if (direction.equals("down")) {
                worldY += speed;
            }
            if (direction.equals("left")) {
                worldX -= speed;
            }
            if (direction.equals("right")) {
                worldX += speed;
            }
        }

        spriteCounter ++; 
        if (spriteCounter > 12){
            if (spriteNumber == 1){
                spriteNumber = 2;
            }
            else if (spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }
}
    // INTERACTING WITH OBJECTS

    // Deletes the object from the map after picking it up
    public void pickObject(int index){
        if (index != 999){

            String objectName = gp.obj[index].name;

            if(objectName.equals("Gold")){
                goldBoneCount += 1;
                gp.obj[index] = null;

            }
            if(objectName.equals("Chest")){
                if(goldBoneCount == 3){
                    gp.obj[index] = null;
                }
                if (gp.obj[index] == null){
                    gp.gameState = gp.finishState;
                }
            }
            if(objectName.equals("Ball")){
                this.speed += 1;
                gp.obj[index] = null;
            }
        }
    }



    // Draws the Player to the GamePanel and switches it between the two sprite images for each
    // corresponding direction to create an animation when Player is moving
    public void draw (Graphics2D g2){

        BufferedImage image = null;

        if (direction.equals("up")) {
            if (spriteNumber == 1){
                image = up1;
            }
            if (spriteNumber == 2){
                image = up2;
            }

        }
        if (direction.equals("down")) {
            if (spriteNumber == 1){
                image = down1;
            }
            if (spriteNumber == 2){
                image = down2;
            }
        }
        if (direction.equals("left")) {
            if (spriteNumber == 1){
                image = left1;
            }
            if (spriteNumber == 2){
                image = left2;
            }
        }
        if (direction.equals("right")) {
            if (spriteNumber == 1){
                image = right1;
            }
            if (spriteNumber == 2){
                image = right2;
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
