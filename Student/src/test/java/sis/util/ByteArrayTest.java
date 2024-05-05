package sis.util;

import org.junit.jupiter.api.Test;
import sis.db.TestData;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayTest {

    @Test
    void size() throws IOException {
        String origin = "This is a test.";
        var byteArray = new ByteArray(origin);
        assertNotEquals(0, byteArray.size());
        String after = (String) ByteArray.toObject(byteArray.getBytes());
        assertNotSame(origin, after);
        assertEquals(origin, after);

        TestData testdata = new TestData("key", "abc", 10);
        var byteArray2 = new ByteArray(testdata);
        TestData after2 = (TestData) ByteArray.toObject(byteArray2.getBytes());
        assertNotSame(testdata, after2);
        assertEquals(testdata, after2);
    }
}