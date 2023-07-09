package Engine;

import Animations.Sprite;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Objects;

public class MusicPlayer {

    Long curr;
    Clip clip;

    AudioInputStream audioInputStream;
    static String filePath;

    boolean on = true;

    public MusicPlayer(String path){
        filePath = "/" + path + ".wav";;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Sprite.class.getResource(path)));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.stop();
        }
        catch (Exception e){
            System.out.println("music exception");
        }
    }

    public void changeTune(String path){
        filePath = path;
        stop();
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            play();
        }
        catch (Exception e){}
    }

    public void play(){
        if(on)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        curr = 0L;
        clip.stop();
    }

}
