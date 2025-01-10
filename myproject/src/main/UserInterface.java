package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



public class UserInterface {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;

    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;


    public UserInterface(GamePanel gp){
        this.gp = gp;

        arial_40 = new Font ("Arial", Font.PLAIN, 40);
    }

    public int getXForCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){
            // Do playState stuff
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if (gp.gameState == gp.finishState){
            drawFinishedScreen();
        }
    }

    // Draws the Title on the screen and the Player can not move 
    public void drawTitleScreen(){

        g2.setColor(Color.GRAY);
        g2.fillRect(0,0, gp.screenWidth,gp.screenHeight);

        // Title words
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String text = "CORGI RUN";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 3;

        // Shadowing for the title words
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // Main title words
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Corgi Image
        x = gp.screenWidth / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.left1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        
        // Title Menu 
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        text = "Begin";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

    }
    

    // Draws the words "Paused" on the screen and the Player can not move 
    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSED";

        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        String text2 = "Press 'P' to resume";
        
        int a = (gp.screenWidth / 5) * 2;
        int b = (gp.screenHeight / 5) * 3;

        g2.drawString(text2, a, b);
    }


    // Draws the words "Completed" on the screen and the Player can not move 
    public void drawFinishedScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String text = "COMPLETED";

        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        String text2 = "Time: ";
        
        int a = (gp.screenWidth / 5) * 2;
        int b = (gp.screenHeight / 5) * 3;

        g2.drawString(text2, a, b);
    }

    // Draw the bone counter in the right corner of the screen

}
