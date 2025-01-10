package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // Increase the tile size originally 16 x 16 pixels by scale of 3
    public int tileSize = 16 * 3;

    // Creates the size of the frame for the gameplay
    // Height determined by number of rows and Width determined by number of columns 
    public final int screenHeight = tileSize * 14;
    public final int screenWidth = tileSize * 18;

    // Initialize the world map's size by making the height and width 50 x 50
    public final int worldNumCol = 50;
    public final int worldNumRow = 50;
    public final int worldWidth = tileSize * 50;
    public final int worldHeight = tileSize * 50;

    // Set FPS (frames per second)
    private final int FPS = 60;

    // Initialize the KeyManager, TileManager, ObjectManager to prepare the map
    public KeyManager keyM = new KeyManager(this);
    public TileManager tileM = new TileManager(this);
    public ObjectManager objectM = new ObjectManager(this);

    // Initialize the SuperObject array to hold 10 unique objects in the game
    public SuperObject obj[] = new SuperObject[10];


    // Initialize the CollisionChecker for checking collisions between Player and tiles, objects
    public CollisionChecker checker = new CollisionChecker(this);

    // Initialize the Player 
    public Player player = new Player(this, keyM);

    public UserInterface ui = new UserInterface(this);

    // Initialize the gameThread
    public Thread gameThread;

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int finishState = 3;


    // GamePanel 
    public GamePanel(){

        // Initialize the gamepanel's features 
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyM);
        this.setFocusable(true);

    }

    // Setting the objects on the map
    public void setUpGame(){
        objectM.setObject();
        gameState = titleState;
    }

    // Starts the game thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Draws the visuals in the game map
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // Drawing tiles
        tileM.draw(g2);

        // Drawing objects
        for (SuperObject obj1 : obj) {
            if (obj1 != null) {
                obj1.draw(g2, this);
            }
        }

        // Drawing player
        player.draw(g2);

        // Draw the word display to user
        ui.draw(g2);
        
        g2.dispose();
    }


    // Update method 
    public void update() {
        if (gameState == playState){
            player.update();
        }
        if (gameState == pauseState){
            // Do nothing
        }

    }

    // Run method 
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } 
            catch (InterruptedException e) {
            }
        }
    }
    
}
