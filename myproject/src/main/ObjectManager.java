package main;

import java.util.Random;
import object.Obj_Ball;
import object.Obj_Chest;
import object.Obj_Gold;

public class ObjectManager {
    public GamePanel gp;

    public ObjectManager (GamePanel gp){
        this.gp = gp;
    }

    // Returns a random integer between the range min-max
    public int randomNum(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;

    }
    // Picks the specified object determined by its index in the SuperObject array and 
    // sets the object's position on the world map 
    public void setObject(){

        int row1 = randomNum(10, 50);
        int col1 = randomNum(10, 50);
        int tileType1 = gp.tileM.mapTileNum[row1][col1];

        // Check if the random row and col index is a non-collision tile (grass or index 0)
        while (tileType1 != 0){
            row1 = randomNum(10, 50);
            col1 = randomNum(10, 50);
            tileType1 = gp.tileM.mapTileNum[row1][col1];
        }

        gp.obj[0] = new Obj_Gold();
        gp.obj[0].worldX = row1 * gp.tileSize;
        gp.obj[0].worldY = col1 * gp.tileSize;


        int row2 = randomNum(10, 50);
        int col2 = randomNum(10, 50);
        int tileType2 = gp.tileM.mapTileNum[row2][col2];

        while (tileType2 != 0){
            row2 = randomNum(10, 50);
            col2 = randomNum(10, 50);
            tileType2 = gp.tileM.mapTileNum[row2][col2];
        }
    
        gp.obj[1] = new Obj_Gold();
        gp.obj[1].worldX = row2 * gp.tileSize;
        gp.obj[1].worldY = col2 * gp.tileSize;



        int row3 = randomNum(10, 50);
        int col3 = randomNum(10, 50);
        int tileType3 = gp.tileM.mapTileNum[row3][col3];

        while (tileType3 != 0){
            row3 = randomNum(10, 50);
            col3 = randomNum(10, 50);
            tileType3 = gp.tileM.mapTileNum[row3][col3];
        }
        
        gp.obj[2] = new Obj_Gold();
        gp.obj[2].worldX = row3 * gp.tileSize;
        gp.obj[2].worldY = col3 * gp.tileSize;


        // Chest object has fixed position on the map
        gp.obj[3] = new Obj_Chest();
        gp.obj[3].worldX = 26 * gp.tileSize;
        gp.obj[3].worldY = 8 * gp.tileSize;
        gp.obj[3].collision = true;



        int row4 = randomNum(10, 50);
        int col4 = randomNum(10, 50);
        int tileType4 = gp.tileM.mapTileNum[row4][col4];

        while (tileType4 != 0){
            row4 = randomNum(10, 50);
            col4 = randomNum(10, 50);
            tileType4 = gp.tileM.mapTileNum[row4][col4];
        }
       
        gp.obj[4] = new Obj_Ball();
        gp.obj[4].worldX = row4 * gp.tileSize;
        gp.obj[4].worldY = col4 * gp.tileSize;
        
    }

}
