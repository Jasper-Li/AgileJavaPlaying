package sis.studentinfo;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseSessionCatalogTest {

    @Test
    void storeAndLoad() throws IOException {
        var english = new CourseSession(
                new Course("English", "101"),
                LocalDate.of(2024, 5, 3)
            );
        english.setCredits(4);
        var japanese = new CourseSession(
            new Course("Japanese", "201"),
            LocalDate.of(2024, 5, 3)
        );
        japanese.setCredits(5);
        var student = new Student("Tom. A. Hanks");
        japanese.enroll(student);

        List<CourseSession> sessions = List.of(
            english, japanese
        );

        var catalog = new CourseCatalog();
        for(var courseSession : sessions)  {
            catalog.add(courseSession);
        }
        List<CourseSession> sessionsRead = catalog.getSessions();
        assertEquals(2, sessionsRead.size());

        final String filename = "CourseSessionCatalogTest.add.txt";
        new File(filename).deleteOnExit();
        try {
            catalog.store(filename);
            assertTrue(new File(filename).exists());
            catalog.clearAll();
            assertEquals(0, sessionsRead.size());

            catalog.load(filename);
            assertEquals(2, sessionsRead.size());
            assertNotSame(sessions, sessionsRead);
            for(int i=0; i<sessions.size(); ++i) {
                var current = sessions.get(i);
                var read = sessionsRead.get(i);
                assertEquals(current.getDepartment(), read.getDepartment());
                assertEquals(current.getNumber(), read.getNumber());
                assertEquals(current.getStartDate(), read.getStartDate());
                assertEquals(current.getCredits(), read.getCredits());
            }
            var japaneseSessionRead = sessionsRead.get(1);
            var studentsRead = japaneseSessionRead.getAllStudents();
            assertEquals(1, studentsRead.size());
            assertEquals(student.getFullName(), studentsRead.get(0).getFullName());
        } finally {
            new File(filename).deleteOnExit();
        }
    }
}
