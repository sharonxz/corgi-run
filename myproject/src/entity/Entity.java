package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    // Fields 
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;
    public String direction;

    // Helps with the animation of the Player's movement by switching between two sprite images 
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    // Each entity has its own "area" that measures how much space it takes up on the map
    // This helps with detecting if another object is within its area, therefore, collision
    public Rectangle solidArea;
    public int solidAreaOriginalX;
    public int solidAreaOriginalY;

    public boolean collisionOn = false;
}
