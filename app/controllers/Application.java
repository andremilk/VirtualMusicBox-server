package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.jukebox.core.UniqueIdentifier;
import models.Playlist;
import models.dao.GenericDAO;
import models.music.Music;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Content;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class Application extends Controller {
    private static GenericDAO dao = new GenericDAO();
    private static long LONG_KEY = 223344; // workaround
    private long lastPlaylistID = 0;



    /**
     * This method just generates a new UUID. It does not persist anything.
     * @return
     */
    public static String generateUUID(long id) {
        return UniqueIdentifier.generateUniqueIdentifier(id + LONG_KEY);
    }

    public static long resolveUUID(String UUID) {
        return UniqueIdentifier.resolveIdentifier(UUID) - LONG_KEY;
    }

    @Transactional
    public Result voteMusic(long id, long id_music) {
        long playlistID = id;
        Playlist p = getPlaylistById(id);
        List<Music> _m = dao.findByAttributeName("Music", "id", Long.toString(id_music));
        Music m = _m.get(0);
        int votes = p.getMusics().get(m);
        p.getMusics().put(m, votes + 1);
        dao.persist(p);
        return ok(p.toString());
    }

    public Result getMusics(long id) {
        return ok((Content) getPlaylistById(id));

    }

    @Transactional
    public Result addMusicToPlaylist(long id, String name) {
        long playlistID = id;
        Playlist p = getPlaylistById(id);
        Music m = new Music(name);
        dao.persist(m);
        p.addMusic(m);
        Logger.debug(p.toString());
        dao.persist(p);
        return created(p.toString());
    }

    /**
     * Fetches and returns a QRCode png file.
     * @param UUID
     * @return A PNG image with the matching QRCode
     */
    public File createQR(String UUID) {
        try {
            // this smells
            URL url = new URL("http://qrickit.com/api/qr?d=http://nosso-app.com/playlists/" + UUID);
            File tmpQRcode = File.createTempFile("tmp-qrcode-", ".png");

            tmpQRcode.deleteOnExit(); // deletes the file when the jvm is exited
            org.apache.commons.io.FileUtils.copyURLToFile(url, tmpQRcode);

            // code 201 with the resource returned
            return tmpQRcode;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // if the qrcode was not properly created, this 500 status is returned
        return null;
    }

    /**
     * This method creates a Playlist object and then returns the matching QRcode to the user.
     * @param name
     * @return
     */
    @play.db.jpa.Transactional
    public Result createNewPlaylist(String name) {
        Playlist newPlaylist = new Playlist(name);
        String UUID = generateUUID(lastPlaylistID + 1);
        newPlaylist.setUUID(UUID);
        dao.persist(newPlaylist);
        dao.flush();
        lastPlaylistID = newPlaylist.getId();
        Logger.debug(newPlaylist.toString());
        return created(createQR(generateUUID(newPlaylist.getId())));
    }

    @Transactional
    public static Playlist getPlaylistById(long id) {
        List<Playlist> playlists = dao.findByAttributeName("Playlist", "id", Long.toString(id));
        return playlists.get(0);
    }
    public Result index() {
        final JsonNode jsonResponse = Json.toJson("Your new application is ready.");
        return ok(jsonResponse);
    }
}
