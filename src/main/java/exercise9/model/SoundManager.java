package exercise9.model;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.AudioClip;
import java.net.URL;

import exercise9.App;

public enum SoundManager
{
    BUBBLECLAP("bubbleclap.mp3"), 
    MEEPO("meepo.mp3"), 
    AWW("aww.mp3"), 
    SLIDE("slide.mp3"), 
    GEM("gem.mp3");
    
    private final String soundName;
    private static URL soundPath;
    private static AudioClip audioClip;
    private static Media gameMusic;
    private static Media winnerMusic;
    public static MediaPlayer GAMEMUSIC;
    public static MediaPlayer WINNERMUSIC;
    
    private SoundManager(final String soundName) {
        this.soundName = soundName;
    }
    
    private String getSoundName() {
        return this.soundName;
    }
    
    public static void play(final SoundManager sound) {
        SoundManager.soundPath = App.class.getResource("sounds/" + sound.getSoundName());
        (SoundManager.audioClip = new AudioClip(SoundManager.soundPath.toString())).play();
    }
    
    public static AudioClip getAudioClip() {
        return SoundManager.audioClip;
    }
    
    static {
        SoundManager.gameMusic = new Media(App.class.getResource("sounds/TownTheme.mp3").toExternalForm());
        SoundManager.winnerMusic = new Media(App.class.getResource("sounds/victory.mp3").toExternalForm());
        SoundManager.GAMEMUSIC = new MediaPlayer(SoundManager.gameMusic);
        SoundManager.WINNERMUSIC = new MediaPlayer(SoundManager.winnerMusic);
    }
}