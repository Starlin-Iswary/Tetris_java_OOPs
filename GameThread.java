package tetris; //'GameThread' class is responsible for repeatedly calling some methods (moveBlockDown, spawnBlock, moveBlockToBackground, clearLines).

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread //'Thread' is an existed class which allows computer to do other tasks while waiting
{
    private GameArea ga;
    private GameForm gf;
    private int score; //to store the score
    private int level = 1; //Initially, level is 1.
    private int scorePerLevel = 3; //Score increase necessary for level increase is 3.
    
    private int pause = 1000; //We shall wait for 1000ms (initially) to fall a block by one unit.
    private int speedupPerLevel = 100; //After increase of level each time, blocks will fall 100ms faster.
    
    public GameThread(GameArea ga, GameForm gf) //constructor
    {
        this.ga = ga;
        this.gf = gf;
        
        gf.updateScore(score); //Every time the game is started, score is set to 0.
        gf.updateLevel(level); //Every time the game is started, level is set to 1.
    }
    
    @Override
    public void run()
    {
        while(true) //infinite loop. This while loop will execute as long as we play the game.
        {
            ga.spawnBlock();
            
            while ( ga.moveBlockDown() ) //while ga.moveBlockDown() == true
            {
                try {
                    Thread.sleep(pause); //'pause' is the time we shall wait for to fall a block by one unit.
                }
                catch (InterruptedException ex) { //It is a exception of 'Thread' which tells what to do while sleeping/waiting.
                    return;
                }
            }
            
            if(ga.isBlockOutOfBounds()) //checks whether position of upper edge of a block is beyond upper edge of screen
            {
                Tetris.gameOver(score);
                break; //If position of upper edge of a block is beyond upper edge of screen, the game is over (the game will be finished).
            }
                
            ga.moveBlockToBackground(); //When a block will not move downwards, we move the block to background (array of previous blocks); so we called this method.
            score += ga.clearLines(); //We need to check whether a line is complete to clear, every time a block is landed. Every time a line is cleared, 'score' gets incremented.
            gf.updateScore(score);
            
            int lvl = score / scorePerLevel + 1; //calculating level
            if(lvl > level) //if result ('lvl') is greater than current level
            {
                level = lvl;
                gf.updateLevel(level);
                pause -= speedupPerLevel; //After increase of level each time, 'pause' will be updated.
            }
        }
    }
}
