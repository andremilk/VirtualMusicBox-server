package models.music;

import models.Playlist;

import javax.persistence.*;

/**
 * Created by dekozo on 3/30/16.
 */
@Entity
@Table(name = "MUSIC")
public class Music {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    /**
     * String representation for the music on playlists.
     */
    @Column(name = "name")
    private String name;

    /**
     * Source for the music.
     *
     */
    @Column(name = "source")
    private String source;

    /*
     * Strategy to play music
     /
    @Column(name = "strategy")
    private PlayingMusicStrategy strategy;

    public void playMusic() {

    }
*/
    public Music(String name, String source) {
        this.name = name;
        this.source = source;
    }

    public Music(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

  /*  public PlayingMusicStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PlayingMusicStrategy strategy) {
        this.strategy = strategy;
    }
*/


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    private Playlist playlists;

    public Playlist getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist playlists) {
        this.playlists = playlists;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", playlists=" + playlists +
                '}';
    }
}
