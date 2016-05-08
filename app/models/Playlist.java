package models;

import models.music.Music;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
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

    @ElementCollection
    private Map<Music, HashSet<User>> musicVoters;

    public Playlist() {
        this.musics = new HashMap<Music, Integer>();
        this.musicVoters = new HashMap<Music, HashSet<User>>();
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
        this.musicVoters.put(music, new HashSet<User>());
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

    public Map<Music, HashSet<User>> getMusicVoters() {
        return musicVoters;
    }

    public void setMusicVoters(Map<Music, HashSet<User>> musicVoters) {
        this.musicVoters = musicVoters;
    }

    public void castVote(Music music, User user) {
        if(this.getMusicVoters().containsKey(music))
            if (this.getMusicVoters().get(music).contains(user))
                return;
        int votes = this.getMusics().get(music);
        votes++;
        this.getMusics().put(music, votes);
        this.getMusicVoters().get(music).add(user);
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
