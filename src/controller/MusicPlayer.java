package controller;

import model.Counter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Vilgot Mattsson
 */

public class MusicPlayer { //behövs det en tråd? //BEHÖVS NOG EN TRÅD
    private Counter counter;

    private AudioInputStream audioInput;
    private Clip clip;

    private ArrayList<File> files;

    private boolean isPlaying;
    private boolean isMuted;
    private boolean isShop;

    public MusicPlayer(Counter counter) {
        this("resources/soundTracks/Scene1.wav","resources/soundTracks/Scene1.wav","resources/soundTracks/Scene1.wav","","");
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
              //  clip.start(); //testa om det behövs både start och loop
                clip.loop(clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }

   /*     if(thread == null) {
            thread = new Thread();
            this.start();
        }  else {
            notifyAll();
        }*/
    }

 /*   @Override
    public void run() {
        while (true) {
            if (isPlaying) { //om inte musiken har stoppats
                for (File musicPath : files) { //går igenom alla filer i files
                    if (musicPath.getName().endsWith(currentTypeOfScene+".wav")) {
                        try {
                         //   bis = new BufferedInputStream(fis = new FileInputStream(musicPath)); //resources/+activeScene+"";
                            audioInput = AudioSystem.getAudioInputStream(musicPath);
                            clip = AudioSystem.getClip();
                            clip.open(audioInput);
                            clip.start();
                            clip.loop(Clip.LOOP_CONTINUOUSLY);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }*/

    // ska endast anropas när GUI:t skiftar scen och möjligtvis endast av startMusic() och då ifall scene inte korrelerar med activeScene
    public void stopMusic() {
            clip.setMicrosecondPosition(0);
            clip.stop(); //om denna bara används 
            isPlaying = false;
    }

    public void audioOnOff() {
        if(isMuted) { //om muted så sätts ljudet på

        } else { //annars mutas ljudet

        }
        isMuted = !isMuted;
    }

    //anropas när spelaren går in i shopen
    public void setShopActive(boolean shopActive) {
        isShop = shopActive;
    }

    //ska endast anropas när användaren trycker på "pause/resume" på GUI:t
    public void pauseResumeMusic() {
        if(isPlaying) {
            clip.stop();
            //gui.setIconLabelToResume
        } else {
            long clipTimePosition = clip.getMicrosecondPosition();
            clip.setMicrosecondPosition(clipTimePosition);
            clip.loop(clip.LOOP_CONTINUOUSLY);
            //gui.setIconLabelToResume
        }
        isPlaying = !isPlaying;
    }
}