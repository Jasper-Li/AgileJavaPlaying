package sis.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

abstract class BaseDataFileTest {
    protected IDataFile file = null;
    abstract IDataFile createInstance() throws IOException;
    abstract String getFileName();

    @BeforeEach
    void setUp() throws IOException {
        file = createInstance();
    }

    @AfterEach
    void tearDown() throws IOException {
        if(file != null)  {
            file.close();
            file.remove();
        }
    }
    @Test
    void create() throws IOException {
        var errorMsgNotExists = STR."File shouldn't exits on removed: \{getFileName()}";
        file.close();
        assertTrue(file.fileExists(), STR."File should exist after closed: \{getFileName()}");
        file.remove();
        assertFalse(file.fileExists(), errorMsgNotExists);
    }
}