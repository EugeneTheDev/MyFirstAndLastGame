package app.music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.LinkedHashMap;

public class MusicPlayer {
    private LinkedHashMap<String,MediaPlayer> tracks;
    private MediaPlayer currentPlayer;
    private MediaPlayer soundEffect;

    public MusicPlayer() {
        this.tracks =new LinkedHashMap<>();
        loadTracks();
    }
    private void loadTracks(){
        tracks.put("start",new MediaPlayer(new Media(MusicPlayer.class.getClassLoader()
                .getResource("music/StartTrack.mp3").toExternalForm())));
        tracks.put("game",new MediaPlayer(new Media(MusicPlayer.class.getClassLoader()
                .getResource("music/GameProcess.mp3").toExternalForm())));
        tracks.put("fight",new MediaPlayer(new Media(MusicPlayer.class.getClassLoader()
                .getResource("music/Fight.mp3").toExternalForm())));
        tracks.put("uhu",new MediaPlayer(new Media(MusicPlayer.class.getClassLoader()
                .getResource("music/Uhuuu.mp3").toExternalForm())));
    }
    public void playTrack(String track){
        if(!tracks.containsKey(track)) return;
        if (currentPlayer!=null)currentPlayer.stop();
        currentPlayer=tracks.get(track);
        currentPlayer.setCycleCount(-1);
        currentPlayer.play();
    }
    public void pause(){
        if (currentPlayer!=null) currentPlayer.pause();
    }
    public void play(){
        if (currentPlayer!=null) currentPlayer.play();
    }
    public void setVolume(double volume){
        if(currentPlayer!=null) currentPlayer.setVolume(volume);
    }
    public void playEffect(String effect){
        if(!tracks.containsKey(effect)) return;
        if(soundEffect!=null) soundEffect.stop();
        soundEffect=tracks.get(effect);
        soundEffect.setVolume(0.2);
        soundEffect.setCycleCount(1);
        soundEffect.play();
    }


}
