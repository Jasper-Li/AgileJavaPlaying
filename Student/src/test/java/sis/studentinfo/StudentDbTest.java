/*
package sis.studentinfo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StudentDbTest {
    @Disabled
    @Test
    void basic() throws IOException {
        assertEquals(0, StudentDb.size());
        var student = new Student("some one name");
        StudentDb.add(student);
        assertEquals(1, StudentDb.size());
        var studentRead = StudentDb.findById(student.getId());
        assertEquals(student.getFullName(), studentRead.getFullName());
        assertEquals(student.getId(), studentRead.getId());
        assertEquals(student.getCredits(), studentRead.getCredits());
    }

}
*/