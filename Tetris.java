package tetris;

import javax.swing.JOptionPane;

public class Tetris
{    
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;
    
    private static AudioPlayer audio = new AudioPlayer();
    
    public static void start() //will show the game form and start the game
    {
        gf.setVisible(true);
        gf.startGame();        
    }
    
    public static void showLeaderboard() //method to display leaderboard form
    {
        lf.setVisible(true); //makes leaderboard form visible
    }
    
    public static void showStartup() //method to display startup form
    {
        sf.setVisible(true); //makes startup form visible
    }
    
    public static void gameOver(int score) //shows 'Game Over!' message & asks user his/her name
    {
        playGameover();
        
        String playerName = JOptionPane.showInputDialog("Game Over!\nPlease enter your name."); //'playerName' stores name of player which is entered.
        
        gf.setVisible(false); //makes gameform invisible
        lf.addPlayer(playerName, score);
    }
    
    public static void playClear() //responsible for playing clearline sound
    {
        audio.playClearline();
    }
    
    public static void playGameover() //responsible for playing gameover sound
    {
        audio.playGameover();
    }
    
    public static void main(String[] args) //instantiates forms & makes startupform visible
    {
        java.awt.EventQueue.invokeLater(new Runnable() { //'invokeLater()' tells what to do while 'Thread' is running. 'new Runnable()' is an instance of an anonymous class that implements 'Runnable' interface and overrides its 'run()' method.
            public void run() {
                
                gf = new GameForm(); //instantiates 'GameForm'
                sf = new StartupForm(); //instantiates 'StartupForm'
                lf = new LeaderboardForm(); //instantiates 'LeaderboardForm'

                sf.setVisible(true); //makes startupform visible
            }
        });        
    }
    
}
