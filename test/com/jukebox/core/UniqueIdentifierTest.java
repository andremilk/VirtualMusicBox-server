package com.jukebox.core;
import static org.junit.Assert.*;

/**
 * Created by dekozo on 3/27/16.
 */
public class UniqueIdentifierTest {

    public final long id = 12345;
    public final String uniqueID = "dnh";


    @org.junit.Test
    public void testGenerateUniqueIdentifier() throws Exception {
        assertTrue(UniqueIdentifier.generateUniqueIdentifier(id).equals(uniqueID));
        assertFalse(UniqueIdentifier.generateUniqueIdentifier((9999L)).equals(uniqueID));
    }

    @org.junit.Test
    public void testResolveIdentifier() throws Exception {
        assertTrue(UniqueIdentifier.resolveIdentifier(uniqueID) == id);
        assertTrue(UniqueIdentifier.resolveIdentifier("abc") != id);
    }
}