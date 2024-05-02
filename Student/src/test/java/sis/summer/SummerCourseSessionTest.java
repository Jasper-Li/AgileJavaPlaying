package sis.summer;

import org.junit.jupiter.api.Test;
import sis.studentinfo.Course;
import sis.studentinfo.CourseSession;
import sis.studentinfo.Session;
import sis.studentinfo.SessionTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SummerCourseSessionTest extends SessionTest {

    @Test
    void getEndDate() {
        var startDate = LocalDate.of(2003, 6, 9);
        var summerSession = new SummerCourseSession(new Course("ENGL", "101"), startDate);
        var endDateExpected = LocalDate.of(2003, 8, 1);
        assertEquals(endDateExpected, summerSession.getEndDate());
    }

    @Override
    protected Session createSession(Course course, LocalDate date) {
        return new SummerCourseSession(course, date);
    }
}