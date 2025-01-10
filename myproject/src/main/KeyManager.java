package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    
    public GamePanel gp;

    // Initialize the key fields if they are pressed or not (true or false) 
    public boolean upPressed;
    public boolean downPressed; 
    public boolean leftPressed;
    public boolean rightPressed;

    public KeyManager(GamePanel gp){
        this.gp = gp;
    }

    // Functions associated with the KeyListener

    // Detects when a key has been typed 
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Detects when a key has been pressed and will change the key fields to true depending on
    // the key pressed's code
    // For making the Player move up (W), down (S), left (A), right (D)
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState){
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }

    }

    // Detects when a key has been released and will change the key fields to false depending on
    // the key released's code
    // For making the Player stop moving in that direction 
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }  
    }
}
