package sis.studentinfo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseSessionTest extends SessionTest {

    @Test
    void count() {
        final var initialCount = 1;
        CourseSession.resetCount(initialCount);
        new CourseSession("ENGL", "101", startDate);
        assertEquals(initialCount + 1 , CourseSession.getCount());
        new CourseSession("ENGL", "101", startDate);
        assertEquals(initialCount + 2, CourseSession.getCount());
        new CourseSession("ENGL", "101", startDate);
        assertEquals(initialCount + 3, CourseSession.getCount());
    }
    @Test
    void getEndDate() {
        var startDate2 = session.getStartDate();
        assertEquals(startDate, startDate2);
        var secondDay = startDate2.plusDays(1);
        assertEquals(startDate, startDate2);
        assertEquals(LocalDate.of(2003, 1, 7), secondDay);

        final var endDate = LocalDate.of(2003, 4, 25);
        assertEquals(endDate, session.getEndDate());
    }

    @Override
    protected Session createSession(String department, String number, LocalDate date) {
        return new CourseSession(department, number, date);
    }
}
