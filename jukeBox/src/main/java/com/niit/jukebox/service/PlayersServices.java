package com.niit.jukebox.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayersServices {
    Clip clip;
    String status;
    Long currentFrame;
    AudioInputStream audioInputStream;
    int songid;

    public void playSong(int songId) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        songid=songId;
        audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/"+songId+".wav"));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        play();

    }
        public void gotoChoiceForPlaylist(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (c)
        {
            case 1:
                stop();
                break;
            case 2:
                pause();
                break;
            case 3:
                resumeAudio();
                break;
            case 4:
                stop();
                break;
            case 5:
                restart();
                break;
            case 6:
                stop();
                break;
        }
    }

    public void gotoChoice(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (c)
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
        }
    }

    public void play()
    {
        clip.start();
        status = "play";
    }
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/" + songid + ".wav"));
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}






