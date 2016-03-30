package models.music;

import models.Playlist;

import javax.persistence.*;

/**
 * Created by dekozo on 3/30/16.
 */
@Entity
@Table(name = "MUSIC")
public class Music {

    @Id
    private String id;
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
*/
    public void playMusic() {

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
