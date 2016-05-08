package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User who can vote on Playlists
 * Created by dekozo on 5/5/16.
 */

@Entity
@Table(name = "USER")
public class User implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "handle")
    private String handle;

    public User() {}

    public User(String handle) {
        this.handle = handle;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public String toString() {
        return "User{" +
                "handle='" + handle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return handle != null ? handle.equals(user.handle) : user.handle == null;

    }

    @Override
    public int hashCode() {
        return 0;
    }
}
