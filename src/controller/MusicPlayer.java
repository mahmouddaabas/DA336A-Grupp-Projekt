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

public class MusicPlayer { //behövs det en tråd?
    private Counter counter;
    private MainFrame mainFrame;

    private AudioInputStream audioInput;
    private Clip clip;

    private FloatControl volume;

    private ArrayList<File> files;

    private boolean isPlaying;
    private boolean isMuted;
    private boolean isShop;

    public MusicPlayer(Counter counter) {
        this("resources/soundTracks/soundMissile.wav","resources/soundTracks/soundCars.wav","resources/soundTracks/soundMissile.wav","resources/soundTracks/soundMissile.wav","");
        this.counter = counter;
    }

    private MusicPlayer(String pathMainMenu, String pathRegularLvl, String pathBossLvl, String pathShop, String pathWinner) {
        files = new ArrayList<>();
        files.add(0, new File(pathMainMenu));
        files.add(1, new File(pathRegularLvl));
        files.add(2, new File(pathBossLvl));
        files.add(3, new File(pathShop));
        files.add(4, new File(pathWinner));
    }
    public void setMainFrame (MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void startMusic() {
        if(isShop) {
            playMusic(files.get(3));
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
                    //reglevels
                    playMusic(files.get(1));
                    break;

                case 5:
                case 10:
                case 15:
                case 20:
                    //bosslevels
                    playMusic(files.get(2));
                    break;
            }
        }
    }

    public void playMusic(File fileToPlay) {
        if(isPlaying) {
            stopMusic();
        }

            try {
                audioInput = AudioSystem.getAudioInputStream(fileToPlay);
                clip = AudioSystem.getClip();
                isPlaying = true;
                clip.open(audioInput);
                clip.loop(clip.LOOP_CONTINUOUSLY);

                if(isMuted) {
                    setPreferredVolume(-50000f);
                } else {
                    setPreferredVolume(-30f);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    // ska endast anropas när GUI:t skiftar scen och möjligtvis endast av startMusic() och då ifall scene inte korrelerar med activeScene
    public void stopMusic() {
        if(isPlaying) {
            clip.stop();
            isPlaying = false;
        }
    }

    private void setPreferredVolume(float preferredVolume) {
        volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(preferredVolume);
    }

    public void audioOnOff() {
        if(isMuted) { //om muted så sätts ljudet på
            setPreferredVolume(-30f);
        } else { //annars mutas ljudet
            setPreferredVolume(-50000f);
        }
        mainFrame.setAudioIcon(isMuted);
        isMuted = !isMuted;
    }

    //anropas när spelaren går in i shopen
    public void setShopActive(boolean shopActive) {
        isShop = shopActive;
    }
}