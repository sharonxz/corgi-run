package main;

import entity.Entity;
import object.SuperObject;

public class CollisionChecker {
    
    public GamePanel gp;

    public CollisionChecker(GamePanel gp){
        // Initialize the GamePanel
        this.gp = gp;
    }

    // Checks if the Player is hitting a specific tile and will prevent it from moving over it
    public void checkTile(Entity entity){
        
        int eLeftWorldX = entity.worldX + entity.solidArea.x;
        int eRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int eTopWorldY = entity.worldY + entity.solidArea.y;
        int eBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int eLeftCol = eLeftWorldX /gp.tileSize;
        int eRightCol = eRightWorldX / gp.tileSize;
        int eTopRow = eTopWorldY / gp.tileSize;
        int eBottomRow = eBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        if (entity.direction.equals("up")) {
            eTopRow = (eTopWorldY - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[eLeftCol][eTopRow];
            tileNum2 = gp.tileM.mapTileNum[eRightCol][eTopRow];

            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
        }
        if (entity.direction.equals("down")) {
            eBottomRow = (eBottomWorldY + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[eLeftCol][eBottomRow];
            tileNum2 = gp.tileM.mapTileNum[eRightCol][eBottomRow];

            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
        }
        if (entity.direction.equals("left")) {
            eLeftCol = (eLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[eLeftCol][eTopRow];
            tileNum2 = gp.tileM.mapTileNum[eLeftCol][eBottomRow];

            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;

            }
        }
        if (entity.direction.equals("right")) {
            eRightCol = (eRightWorldX + entity.speed)/gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[eRightCol][eTopRow];
            tileNum2 = gp.tileM.mapTileNum[eRightCol][eBottomRow];

            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                entity.collisionOn = true;
            }
        }

    }
    // Check if the Player (not a NPC) is hitting an object and will return the index of the object.
    public int checkObject (Entity entity, boolean checkIfPlayer){
        
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++){
            SuperObject object = gp.obj[i];
            if (object != null){
                entity.solidArea.x = entity.worldX + entity.solidAreaOriginalX;
                entity.solidArea.y = entity.worldY + entity.solidAreaOriginalY;

                object.solidArea.x = object.worldX + object.solidAreaOriginalX;
                object.solidArea.y = object.worldY + object.solidAreaOriginalY;

                // Check entity's direction in regards to the object

                if (entity.direction.equals("up")) {
                    entity.solidArea.y -= entity.speed;
                    
                    // Check if the entity and object intersect
                    if(entity.solidArea.intersects(object.solidArea)){
                        if (object.collision == true){
                            entity.collisionOn = true;
                        }
                    
                        if (checkIfPlayer == true){
                            index = i;
                        }
                    }
                }
                if (entity.direction.equals("down")) {
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(object.solidArea)){
                        if (object.collision == true){
                            entity.collisionOn = true;
                        }
                    
                        if (checkIfPlayer == true){
                            index = i;
                        }
                    }
                }
                if (entity.direction.equals("left")) {
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(object.solidArea)){
                        if (object.collision == true){
                            entity.collisionOn = true;
                        }
                    
                        if (checkIfPlayer == true){
                            index = i;
                        }
                    }
                }
                if (entity.direction.equals("right")) {
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(object.solidArea)){
                        if (object.collision == true){
                            entity.collisionOn = true;
                        }
                    
                        if (checkIfPlayer == true){
                            index = i;
                        }
                    }                   
                }

                // Resetting the x and y coordinates
                entity.solidArea.x = entity.solidAreaOriginalX;
                entity.solidArea.y = entity.solidAreaOriginalY;
                object.solidArea.x = object.solidAreaOriginalX;
                object.solidArea.y = object.solidAreaOriginalY;

            }
        }
        return index;
    }
}
