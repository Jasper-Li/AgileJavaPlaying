package sis.db;

import org.junit.jupiter.api.Test;
import sis.util.ByteArray;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataFileTest extends BaseDataFileTest {
    private static String FILENAME = "DataFileTest";

    @Override
    IDataFile createInstance() throws FileNotFoundException {
        return new DataFile(FILENAME);
    }

    @Override
    String getFileName() {
        return FILENAME;
    }

    @Test
    void add() throws IOException {
        DataFile dataFile = (DataFile) file;
        int currentLocation = 0;
        assertEquals(currentLocation, dataFile.size());

        var testData1 = new TestData("id1", "field10", 100);
        var testData2 = new TestData("id2", "field20", 200);
        IndexFile.Entry entry1 = verifyAppend(dataFile, currentLocation, testData1);
        currentLocation += entry1.size();
        IndexFile.Entry entry2 = verifyAppend(dataFile, currentLocation, testData2);
        verifyFind(dataFile, entry1, testData1);
        verifyFind(dataFile, entry2, testData2);
        dataFile.close();

        dataFile = new DataFile(FILENAME);
        verifyFind(dataFile, entry1, testData1);
        verifyFind(dataFile, entry2, testData2);
        dataFile.close();
        dataFile.remove();
    }
    IndexFile.Entry verifyAppend(DataFile dataFile, long currentLocation, TestData testData) throws IOException {
        ByteArray byteArray = new ByteArray(testData);
        TestData elementRead = (TestData) ByteArray.toObject(byteArray.getBytes());
        assertEquals(testData, elementRead);
        var entry = new IndexFile.Entry(dataFile.size(), byteArray.size());
        dataFile.append(byteArray);
        currentLocation += byteArray.size();
        assertEquals(currentLocation, dataFile.size());
        return entry;
    }
    void verifyFind(DataFile dataFile, IndexFile.Entry entry, TestData testData) throws IOException {
        var elementRead = (TestData) dataFile.find(entry);
        assertEquals(testData, elementRead);
    }
}