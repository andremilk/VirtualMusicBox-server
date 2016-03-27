package com.jukebox.core;

/** This class should provide methods to create unique identifiers with a mappable URL as well as it's QRCode.
 * Created by dekozo on 3/22/16.
 */
public class UniqueIdentifier {
    public static String map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * http://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/ reference
     */

    public static String generateUniqueIdentifier(long index) {
        StringBuilder uniqueIdentifier = new StringBuilder();
        while(index > 1) {
            uniqueIdentifier.insert(0, String.valueOf(map.charAt((int) (index % 62))));
            index = index/62;
          }
        return uniqueIdentifier.toString();
    }

    public static long resolveIdentifier(String identifier) {
        long id = 0;

        for (int i = 0; i < identifier.length(); i++) {
            if ('a' <= identifier.charAt(i) && identifier.charAt(i) <= 'z')
                id = id * 62 + identifier.charAt(i) - 'a';
            if ('A' <= identifier.charAt(i) && identifier.charAt(i) <= 'Z')
                id = id * 62 + identifier.charAt(i) - 'A' + 26;
            if ('0' <= identifier.charAt(i) && identifier.charAt(i) <= '9')
                id = id * 62 + identifier.charAt(i) - '0' + 52;
        }
        return id;
    }
}
