package sis.db;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseTest extends BaseDataFileTest {
    private static String DATABASE_BASENAME = "DataBaseTest";

    @Override
    IDataFile createInstance() throws IOException {
        return new DataBase(DATABASE_BASENAME);
    }

    @Override
    String getFileName() {
        return DATABASE_BASENAME;
    }

    @Test
    void add() throws IOException {
        List<TestData> testData = List.of(
            new TestData("id1", "field10", 100),
            new TestData("id2", "field20", 200)
        );
        DataBase database = (DataBase) file;
        assertFalse(database.fileExists());
        assertEquals(0, database.count());
        for(int i =0; i<testData.size(); ++i) {
            var element = testData.get(i);
            database.add(element.key(), element);
            assertEquals(i+1, database.count());
        }
        database.close();

        database = new DataBase(DATABASE_BASENAME);
        for(var expectedTestData : testData) {
            TestData read = (TestData) database.find(expectedTestData.key());
            assertEquals(expectedTestData, read);
        }
        database.close();
    }

}