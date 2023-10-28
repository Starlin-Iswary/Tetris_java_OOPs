package tetris;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer //This class is responsible for downloading the audio files & playing the sounds
{
    private String soundsFolder = "tetrissounds" + File.separator; //Inside "tetrissounds" folder, audio files are present. 'File.separator' (stores '\') is path separator of 'soundsFolder'.
    private String clearlinePath = soundsFolder + "line.wav.crdownload"; //stores path of "line.wav.crdownload" file
    private String gameoverPath = soundsFolder + "success.wav.crdownload"; //stores path of "success.wav.crdownload" file
    
    private Clip clearlineSound, gameoverSound; //'Clip' objects are used to play the sounds.
    
    public AudioPlayer() //constructor
    {
        try
        {
            clearlineSound = AudioSystem.getClip(); //'getClip()' throws exception that we need to catch. Initializing 'Clip' object 'clearlineSound'
            gameoverSound = AudioSystem.getClip(); //'getClip()' throws exception that we need to catch. Initializing 'Clip' object 'gameoverSound'
            
            clearlineSound.open( AudioSystem.getAudioInputStream( new File(clearlinePath).getAbsoluteFile() ) );
            
            gameoverSound.open( AudioSystem.getAudioInputStream( new File(gameoverPath).getAbsoluteFile() ) );
            
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) { //'UnsupportedAudioFileException' is thrown when program can't play audio file.
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) { //'IOException' is thrown when the file can't be found.
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playClearline() //responsible for playing clearline sound-clip
    {
        clearlineSound.setFramePosition(0); //so that sound starts playing from beginning each time
        clearlineSound.start();
    }
    
    public void playGameover() //responsible for playing gameover sound-clip
    {
        gameoverSound.setFramePosition(0); //so that sound starts playing from beginning each time
        gameoverSound.start();
    }
}
