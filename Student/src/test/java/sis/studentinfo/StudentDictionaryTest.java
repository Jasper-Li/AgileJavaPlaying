package sis.studentinfo;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDictionaryTest {
    private static final String FILENAME_BASE = "StudentDictionaryTest";
    private static final int NUMBER_OF_STUDENTS = 10;

    @Test
    void basic() throws IOException {
        var dictionary = new StudentDictionary(FILENAME_BASE);
        assertFalse(dictionary.fileExists());

        for(int i = 0; i< NUMBER_OF_STUDENTS; ++i) {
            final String id = String.valueOf(i);
            final var student = new Student(id);
            student.setId(id);
            student.addCredits(i);
            dictionary.add(student);
        }
        verifyStudentLookUp(dictionary);
        dictionary.close();
        assertTrue(dictionary.fileExists());

        dictionary = new StudentDictionary(FILENAME_BASE);
        verifyStudentLookUp(dictionary);
        dictionary.close();
        dictionary.remove();
        assertFalse(dictionary.fileExists());
    }
    void verifyStudentLookUp(StudentDictionary dictionary) throws IOException {
        for(int i = 0; i< NUMBER_OF_STUDENTS; ++i) {
            final String id = String.valueOf(i);
            final Student student = dictionary.findById(id);
            assertNotNull(student);
            assertEquals(id, student.getLastName());
            assertEquals(id, student.getId());
            assertEquals(i, student.getCredits());
        }
    }
}
