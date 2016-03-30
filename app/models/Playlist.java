package models;

import models.music.Music;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dekozo on 3/30/16.
 */

@Entity
@Table(name = "PLAYLIST")
public class Playlist {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "uuid")
    private String UUID;


    @ElementCollection
    private Map<Music, Integer> musics;

    public Playlist() {
        this.musics = new HashMap<Music, Integer>();
    }

    public Playlist(String name) {
        this();
        this.name = name;
    }

    public Playlist(String name, String UUID) {
        this();
        this.name = name;
        this.UUID = UUID;
    }

    public void addMusic(String name) {
        this.musics.put(new Music(name, "src"), 0);
    }

    public void addMusic(Music music) {
        this.musics.put(music, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Map<Music, Integer> getMusics() {
        return musics;
    }

    public void setMusics(Map<Music, Integer> musics) {
        this.musics = musics;
    }



    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", UUID='" + UUID + '\'' +
                ", musics=" + musics +
                '}';
    }
}
