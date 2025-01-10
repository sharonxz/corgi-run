package main;
import javax.swing.JFrame;

public class Application {
    public static void main(String[] args) {

        // Creates the frame which the game is played on
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Corgi Run");
        
        // Creates the gamepanel on the frame for gameplay 
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.pack(); 
        
        // Prepares the game map and begins the game thread
        gamePanel.setUpGame();
        gamePanel.startGameThread();

    }

}
