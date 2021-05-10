package controller;

import model.Counter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MusicPlayer implements ActionListener { //behövs det en tråd?
    private Counter counter;

    private AudioInputStream audioInput;
    private Clip clip;

    private int currentTypeOfScene; //för startmetoden
    private ArrayList<File> files;

    private boolean isPlaying;
    private boolean isMuted;

    public MusicPlayer() {
        this("resources/music/Scene1.wav","","","","");
    }

    private MusicPlayer(String pathMainMenu, String pathRegularLvl, String pathBossLvl, String pathShop, String pathWinner) {
        this.counter = new Counter();
        files = new ArrayList<>();
        files.add(0, new File(pathMainMenu));
        files.add(1, new File(pathRegularLvl));
        files.add(2, new File(pathBossLvl));
        files.add(3, new File(pathShop));
        files.add(4, new File(pathWinner));
    }

    public void startMusic() {
        switch (counter.getCurrentScene()) {
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

            default:
            playMusic(files.get(3));
            //shop
        }
    }

    public void playMusic(File fileToPlay) {
        stopMusic();

            try {
                audioInput = AudioSystem.getAudioInputStream(fileToPlay);
                clip = AudioSystem.getClip();
            //    clip.
                clip.open(audioInput);
                clip.start(); //testa om det behövs både start och loop
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
            isPlaying = false;
    }

    public void audioOnOff() {
        if(isMuted) { //om muted så sätts ljudet på

        } else { //annars mutas ljudet

        }
        isMuted = !isMuted;
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


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "pause/resume":
                pauseResumeMusic();
                break;

            default:
                System.out.println("Music-Button Action-command not corresponding");
        }
    }
}