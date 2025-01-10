package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public final class TileManager {
    public GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager (GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.worldNumCol][gp.worldNumRow];
        getTileImage();
        loadMap("/Users/sharonzhengg/fluffyunicorn/myproject/res/maps/world.txt");
    }

    // Set the index of the Tile object with a image file corresponding to a specific tile design
    // Uses try-catch statement because of the ImageIo.read function
    public void getTileImage() {
        try {

            // Grass tile
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/tiles/light_grass.png"));
            
            // Tree tile
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/tiles/tree.png"));
            tile[1].collision = true;

            // Flowers tile
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/tiles/flowers.png"));
            
            // Path tile
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/tiles/path.png"));

            // Water tile
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/tiles/water.png"));
            tile[4].collision = true;

            // Bridge tile
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/tiles/bridge.png"));

            // Bush tile
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/tiles/bush.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("/Users/sharonzhengg/fluffyunicorn/myproject/res/tiles/water2.png"));
            tile[7].collision = true;

        }catch (IOException e){
        }
    }

    // Loads the tiles based off the world map txt file by seeing the number in the file 
    // and corresponding them to the tile index in the tile array
    public void loadMap(String fileName) {

        try {
            InputStream inputStream = (new FileInputStream(new File(fileName)));
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                int col = 0;
                int row = 0;
                
                while (col < gp.worldNumCol && row < gp.worldNumRow){
                    String line = bufferedReader.readLine();
                    
                    while (col < gp.worldNumCol){
                        String numbers[] = line.split(" ");
                        
                        int num = Integer.parseInt(numbers[col]);
                        
                        mapTileNum[col][row] = num;
                        col ++;
                    }
                    if (col == gp.worldNumCol){
                        col = 0;
                        row++;
                    }
                }
            }
            
        }catch (IOException | NumberFormatException e){
        }

    }

    // Draw each tile row by row  
    public void draw(Graphics2D g2){

        // Starts the row and col at 0,0
        int worldCol = 0;
        int worldRow = 0;

        // While-loop until it reaches the max number of rows and cols in the world map (50,50)
        while (worldCol < gp.worldNumCol && worldRow < gp.worldNumRow){
            
            // Finds the index of the tile image from the world map txt file based on 
            // the row and col point
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            }

            // Increments worldCol and will reset the variable to zero once it reaches the 
            // end of the row and move to the next row
            worldCol ++ ;
            if (worldCol == gp.worldNumCol){
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}


