package org.example.chapter11;


import org.junit.jupiter.api.Test;

import javax.swing.plaf.ButtonUI;
import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class Test1WriteReadTest {
    String readWrite(String filename, String content) throws IOException {
        var file = new File(filename).toPath();
        Files.deleteIfExists(file);
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(content);
        } finally {
            if (writer!=null) {
                writer.close();
            }
        }
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine())!= null) {
                builder.append(STR."\{line}\n");
            }
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
        Files.deleteIfExists(file);
        return builder.toString();
    }
    @Test
    void readWriteTest() throws IOException {
        String content = """
            Create a test to write the text of this exercise to the file system. The
            test should read the file back in and make assertions about the content.
            Ensure that you can run the test multiple times and have it pass.
            Finally, make sure that there are no leftover files when the test finishes,
            even if an exception is thrown.
            """;
        var read = readWrite("Chapter11_1.txt", content);
        assertNotSame(content, read);
        assertEquals(content, read);
    }
}
