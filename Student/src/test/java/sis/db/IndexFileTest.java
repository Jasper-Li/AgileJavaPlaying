package sis.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IndexFileTest extends BaseDataFileTest{
    private static final String FILENAME = "IndexFileTest.bin";
    private IndexFile indexFile=null;
    @BeforeEach
    @Override
    void setUp() throws IOException {
        super.setUp();
        indexFile = (IndexFile) file;
    }

    @AfterEach
    @Override
    void tearDown() throws IOException {
        indexFile = null;
        super.tearDown();
    }
    @Override
    IDataFile createInstance() throws IOException {
        return new IndexFile(FILENAME);
    }

    @Override
    String getFileName() {
        return FILENAME;
    }

    @Test
    void entryNotFound() {
        assertNull(indexFile.find("NoSuchKey"));
    }
    @Test
    void add() throws IOException {
        assertEquals(0, indexFile.count());

        record Check(String key, IndexFile.Entry entry){}
        List<Check> checks = List.of(
            new Check("key1", new IndexFile.Entry(0, 100)),
            new Check("key2", new IndexFile.Entry(100, 500)),
            new Check("key3", new IndexFile.Entry(600, 50))
        );

        var size = checks.size();
        for(int i = 0; i < size; ++i) {
            var check = checks.get(i);
            indexFile.add(check.key, check.entry);
            assertEquals(i+1, indexFile.count());

            var entryRead = indexFile.find(check.key);
            assertEquals(check.entry, entryRead);
        }

        indexFile.close();
        indexFile = new IndexFile(FILENAME);

        for(int i = 0; i < size; ++i) {
            var check = checks.get(i);
            var entryRead = indexFile.find(check.key);
            assertEquals(check.entry, entryRead);
        }
    }
}