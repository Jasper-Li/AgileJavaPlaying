package sis.studentinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class SessionTest {
    protected Session session;
    protected LocalDate startDate;

    @BeforeEach
    void setUp() {
        startDate = LocalDate.of(2003, 1, 6);
        session = createSession("ENGL", "101", startDate);;
    }
    protected abstract Session createSession(String department, String number, LocalDate date);
    @Test
    void create() {
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
        assertEquals(0, session.getNumberOfStudent());
    }

    @Test
    void enrollStudent() {
        final var sessionCredits = 3;
        session.setCredits(sessionCredits);

        var student1 = new Student("Cain Devoe");
        session.enroll(student1);
        assertEquals(1, session.getNumberOfStudent());
        assertEquals(student1, session.getStudentBy(0));
        assertEquals(sessionCredits, student1.getCredits());

        var student2 = new Student("Coralee, Devaghn");
        final var oldCreditsBase = 10;
        student2.addCredits(oldCreditsBase);
        session.enroll(student2);
        assertEquals(2, session.getNumberOfStudent());
        var newCredits2 = student2.getCredits();
        assertEquals(sessionCredits, newCredits2 - oldCreditsBase);

        assertEquals(student1, session.getStudentBy(0));
        assertEquals(student2, session.getStudentBy(1));
    }
    @Test
    void comparable() {
        var sessionC = createSession("CMSC", "200", startDate);
        var sessionE = createSession("ENGL", "101", startDate);
        var sessionE2 = createSession("ENGL", "201", startDate);
        assertEquals(0, session.compareTo(sessionE));

        assertTrue(session.compareTo(sessionC) > 0);
        assertTrue(sessionC.compareTo(session) < 0);
        assertTrue(session.compareTo(sessionE2) < 0);
    }
    @Test
    void getSessionLength() {
        assertThat(session.getSessionLength(), greaterThan(0));
    }
}
