package controller;

import model.Counter;
import view.MainFrame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Vilgot Mattsson
 * This class provides the game with backgroundmusic which change depending on the scene that is currently displaying.
 */

public class MusicPlayer {
    private Counter counter;
    private MainFrame mainFrame;

    private Clip clip;
    private Clip clipTimer;
    private FloatControl volume;

    private boolean isPlaying;
    private boolean isMuted;
    private boolean isShop;
    private boolean ticking;

    /**
     * Constructor that provides the private constructor with file paths to all of the audio clips
     * @param counter A counter object to be used to get information regarding current level and scene
     */
    public MusicPlayer(Counter counter) {
        this.counter = counter;
    }

    /**
     * Method used by GameLogic to provide this class with GameLogic's MainFrame object
     * @param mainFrame the MainFrame object to be passed to this class' MainFrame variable
     */
    public void setMainFrame (MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Method that first determines which audio clip to be played based on the currently displaying scene and then provides
     * the playMusic-method with the filepath to the desired audio clip
     */
    public void startMusic() {
        if(isShop) {
            //shop scene
            clip.stop();
            clip = play("resources/soundTracks/ShopSound.wav", -30f);
        } else {
            switch (counter.getLevel()) {
                case 0:
                    //startup screen
                    clip = play("resources/soundTracks/MainMenuSound.wav", -30f);
                    break;

                case 1:clip.stop();
                case 2:
                case 3:
                case 4:
                case 6:
                case 7:
                case 8:
                case 9:
                case 11:
                case 12:
                case 13:
                case 14:
                case 16:
                case 17:
                case 18:
                case 19:
                    //regular levels
                    clip = play("resources/soundTracks/regularLevelSound.wav", -30f);
                    break;

                case 5:
                case 10:
                case 15:
                case 20:
                    //boss levels
                    clip.stop();
                    clip = play("resources/soundTracks/bossFightSound.wav", -30f);
                    break;
            }
        }
    }

    /**
     * Method that starts the audio.
     * @param filename
     * @param volume
     * @return clip
     */
    public Clip play(String filename, float volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // values have min/max values, for now don't check for outOfBounds values
            FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    /**
     * Method that starts the audio for the timer.
     * @param filename
     * @param volume
     * @return clip
     */
    public Clip playTimer(String filename, float volume) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            clipTimer = AudioSystem.getClip();
            clipTimer.open(audioInputStream);
            clipTimer.setFramePosition(0);
            clipTimer.loop(Clip.LOOP_CONTINUOUSLY);

            // values have min/max values, for now don't check for outOfBounds values
            FloatControl gainControl = (FloatControl)clipTimer.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return clipTimer;
    }

    /**
     * Method that stops the audio if playing
     */
    public void stopMusic() {
        if(isPlaying) {
            clip.stop();
            clipTimer.stop();
            isPlaying = false;
        }
    }

    /**
     * Private method that sets the volume of the audio
     * @param preferredVolume the float value to be set as volume
     */
    private void setPreferredVolume(float preferredVolume) {
        volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(preferredVolume);
    }

    /**
     * Method that turns the audio on/off depending on the value of boolean variable isMute
     * and then notifies the MainFrame about the change of state
     */
    public void audioOnOff() {
        if(isMuted) {
            setPreferredVolume(-30f);
        } else {
            setPreferredVolume(-50000f);
        }
        isMuted = !isMuted;
        mainFrame.setAudioIcon(isMuted);
    }

    /**
     * Starts the ticking sound when called.
     */
    public void startTicking() {
        if(!ticking) {
            ticking = true;
            clipTimer = playTimer("resources/soundTracks/TickingClock.wav", -30f);
        }
    }

    /**
     * Stops the ticking sound when called.
     */
    public void stopTicking() {
        if(ticking) {
            ticking = false;
            clipTimer.stop();
        }
    }

    /**
     * Method that sets a boolean value to the variable isShop
     * @param shopActive the boolean value to set on isShop
     */
    public void setShopActive(boolean shopActive) {
        isShop = shopActive;
    }
}
