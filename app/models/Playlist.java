package models;

import com.jukebox.core.UniqueIdentifier;
import models.music.Music;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "musics")
    private List<Music> musics;

    public Playlist() {
        this.musics = new ArrayList<Music>();
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
        this.musics.add(new Music(name, "src"));
    }

    public void addMusic(Music music) {
        this.musics.add(music);
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

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(ArrayList<Music> musics) {
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
