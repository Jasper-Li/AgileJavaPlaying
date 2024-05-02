package sis.studentinfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentDictionaryTest {
    @Test
    void basic() {
        final var dictionary = new StudentDictionary();
        final int numberOfStudents = 10;

        for(int i = 0; i<numberOfStudents; ++i) {
            final String id = String.valueOf(i);
            final var student = new Student(id);
            student.setId(id);
            student.addCredits(i);
            dictionary.add(student);
        }
        for(int i = 0; i<numberOfStudents; ++i) {
            final String id = String.valueOf(i);
            final Student student = dictionary.findById(id);
            assertEquals(id, student.getLastName());
            assertEquals(id, student.getId());
            assertEquals(i, student.getCredits());

        }
    }
}
