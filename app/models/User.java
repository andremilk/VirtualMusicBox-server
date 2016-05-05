package models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User who can vote on Playlists
 * Created by dekozo on 5/5/16.
 */

public class User {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "handle")
    private String handle;

    public User(String handle) {
        this.handle = handle;
    }
}
