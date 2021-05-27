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

    private ArrayList<File> files;

    private AudioInputStream audioInput;
    private Clip clipMusic;
    private Clip clipSoundEffects;
    private FloatControl volume;

    private boolean isPlaying;
    private boolean isMuted;
    private boolean isShop;
    private boolean gameOver;
    private boolean isWinner;
    private boolean ticking;

    /**
     * Constructor that provides the private constructor with file paths to all of the audio clips
     * @param counter A counter object to be used to get information regarding current level and scene
     */
    public MusicPlayer(Counter counter) {
        this("resources/soundtracks/mainMenuSound.wav","resources/soundtracks/regularLevelSound.wav",
                "resources/soundtracks/bossFightSound.wav","resources/soundtracks/ShopSound.wav",
                "resources/soundtracks/gameOverSound.wav","");
        this.counter = counter;
    }

    /**
     * Private constructor which initializes the ArrayList files and passes the values of the parameters to the
     * creation of multiple File objects
     * @param pathMainMenu filepath to the audio to be played on the MainMenu-screen/Startup-screen
     * @param pathRegularLvl filepath to the audio to be played on all of the levels with regular monsters
     * @param pathBossLvl filepath to the audio to be played on all of the levels with bosses
     * @param pathShop filepath to the audio to be played while player is at the shop
     * @param pathWinner filepath to the audio to be played when player reaches the winner-screen
     */
    private MusicPlayer(String pathMainMenu, String pathRegularLvl, String pathBossLvl, String pathShop, String gameOver, String pathWinner) {
        files = new ArrayList<>();
        files.add(0, new File(pathMainMenu));
        files.add(1, new File(pathRegularLvl));
        files.add(2, new File(pathBossLvl));
        files.add(3, new File(pathShop));
        files.add(4, new File(gameOver));
        files.add(5, new File(pathWinner));
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
        if (isShop) {
            //shop scene
            playMusic(files.get(3));
        } else if (gameOver) {
            playMusic(files.get(4));
        } else if (isWinner) {
            playMusic(files.get(5));
        } else {
            switch (counter.getLevel()) {
                case 0:
                    //startup screen
                    playMusic(files.get(0));
                    break;

                case 1:
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
                    playMusic(files.get(1));
                    break;

                case 5:
                case 10:
                case 15:
                case 20:
                    //boss levels
                    playMusic(files.get(2));
                    break;
            }
        }
    }

    /**
     * Private method that uses an AudioInputStream and a Clip object to play audio
     * Checks if any audio is playing and in that case stops the music before playing the audio
     * @param fileToPlay A File object with the filepath to the audio which is used to pass to the AudioInputStream and to be played
     */
    private void playMusic(File fileToPlay) {
        if (isPlaying) {
            stopMusic();
        }

        try {
            audioInput = AudioSystem.getAudioInputStream(fileToPlay);
            clipMusic = AudioSystem.getClip();
            isPlaying = true;
            clipMusic.open(audioInput);
            clipMusic.loop(clipMusic.LOOP_CONTINUOUSLY);

            if (isMuted) {
                setPreferredVolume(-50000f, clipMusic);
            } else {
                setPreferredVolume(-30f, clipMusic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSoundEffects(String filePath) {
        try {
            audioInput = AudioSystem.getAudioInputStream(new File(filePath));
            clipSoundEffects = AudioSystem.getClip();
            clipSoundEffects.open(audioInput);
            clipSoundEffects.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setPreferredVolume(-20f, clipSoundEffects);
    }


    /**
     * Method that stops the audio if playing
     */
    public void stopMusic() {
        if (isPlaying) {
            clipMusic.stop();
            isPlaying = false;
        }
    }

    public void stopSoundEffect() {
        clipSoundEffects.stop();
    }

    /**
     * Private method that sets the volume of the audio
     * @param preferredVolume the float value to be set as volume
     */
    private void setPreferredVolume(float preferredVolume, Clip clip) {
        volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(preferredVolume);
    }

    /**
     * Method that turns the audio on/off depending on the value of boolean variable isMute
     * and then notifies the MainFrame about the change of state
     */
    public void audioOnOff() {
        if (isMuted) {
            setPreferredVolume(-30f, clipMusic);
        } else {
            setPreferredVolume(-50000f, clipMusic);
        }
        isMuted = !isMuted;
        mainFrame.setAudioIcon(isMuted);
    }

    /**
     * Starts the clock effect.
     */
    public void startTicking() {
        if (!ticking) {
            ticking = true;
            playSoundEffects("resources/soundtracks/TickingClock.wav");
        }
    }

    /**
     * Stops the clock effect.
     */
    public void stopTicking() {
        if (ticking) {
            ticking = false;
            clipSoundEffects.stop();
        }
    }

    /**
     * Method that sets a boolean value to the variable isShop
     * @param shopActive the boolean value to set on isShop
     */
    public void setShopActive(boolean shopActive) {
        isShop = shopActive;
    }

    public void setGameOverActive(boolean gameOverActive) {
        gameOver = gameOverActive;
    }

    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
}
