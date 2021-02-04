package view;

import controller.ControllerForView;
import controller.IControllerForView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioManager {

    private static AudioManager instance = null;

    String music = "utils/msc/Song_Machine_Theme_Tune.wav";
    File musicPath = new File(music);
    AudioInputStream audioInput = null;
    Clip clip = null;

    String music1 = "utils/msc/The_Ultra_Lancers_-_Retro_Lancers_Audio.wav";
    File musicPath1 = new File(music1);
    AudioInputStream audioInput1 = null;
    Clip clip1 = null;

    String music2 ="utils/msc/Game_over.wav";
    File musicPath2 = new File(music2);
    AudioInputStream audioInput2 = null;
    Clip clip2 = null;

    String audio ="utils/msc/Shoot.wav";
    File audioPath = new File(audio);
    AudioInputStream aInput = null;
    Clip aud = null;

    String audio1 ="utils/msc/Explosion_Sound_Effect.wav";
    File audioPath1 = new File(audio1);
    AudioInputStream aInput1 = null;
    Clip aud1 = null;

    String audio2 ="utils/msc/Wilhelm_Scream_Sound_Effe.wav";
    File audioPath2 = new File(audio2);
    AudioInputStream aInput2 = null;
    Clip aud2 = null;

    String audio3 ="utils/msc/Scream_Monster.wav";
    File audioPath3 = new File(audio3);
    AudioInputStream aInput3 = null;
    Clip aud3 = null;

    String audio4 ="utils/msc/Game_Over_Se.wav";
    File audioPath4 = new File(audio4);
    AudioInputStream aInput4 = null;
    Clip aud4 = null;

    public void PlayMainMenuSong(){

        try {
            audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void StopMainMenuSong(){
        clip.stop();
    }

    public void PlayGameSong(){

        try {
            audioInput1 = AudioSystem.getAudioInputStream(musicPath1);
            clip1 = AudioSystem.getClip();
            clip1.open(audioInput1);
            clip1.start();
            clip1.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void StopGameSong(){
        clip1.stop();
    }

    public void PlayGameOverSong(){

        try {
            audioInput2 = AudioSystem.getAudioInputStream(musicPath2);
            clip2 = AudioSystem.getClip();
            clip2.open(audioInput2);
            clip2.start();
            clip2.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void StopGameOverSong(){
        clip2.stop();
    }


    public void PlayShoot(){

        try {
            aInput = AudioSystem.getAudioInputStream(audioPath);
            aud = AudioSystem.getClip();
            aud.open(aInput);
            aud.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void PlayExploding(){

        try {
            aInput1 = AudioSystem.getAudioInputStream(audioPath1);
            aud1 = AudioSystem.getClip();
            aud1.open(aInput1);
            aud1.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void PlayScream(){
        try {
            aInput2 = AudioSystem.getAudioInputStream(audioPath2);
            aud2 = AudioSystem.getClip();
            aud2.open(aInput2);
            aud2.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void PlayScreamMonster(){
        try {
            aInput3 = AudioSystem.getAudioInputStream(audioPath3);
            aud3 = AudioSystem.getClip();
            aud3.open(aInput3);
            aud3.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void PlayGameOverSe(){
        try {
            aInput4 = AudioSystem.getAudioInputStream(audioPath4);
            aud4 = AudioSystem.getClip();
            aud4.open(aInput4);
            aud4.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static AudioManager getInstance() {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

}