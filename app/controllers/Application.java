package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.jukebox.core.UniqueIdentifier;
import models.dao.GenericDAO;
import models.Playlist;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Application extends Controller {
    private static GenericDAO dao = new GenericDAO();


    /**
     * This method just generates a new UUID. It does not persist anything.
     * @return
     */
    public static String generateUUID(long id) {
        return UniqueIdentifier.generateUniqueIdentifier(id);
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
        String UUID = generateUUID(newPlaylist.getId());
        dao.persist(newPlaylist);
        return created(createQR(generateUUID(newPlaylist.getId())));
    }

    public Result index() {
        final JsonNode jsonResponse = Json.toJson("Your new application is ready.");
        return ok(jsonResponse);
    }
}
