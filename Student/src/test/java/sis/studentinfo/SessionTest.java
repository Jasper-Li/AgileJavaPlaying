package sis.studentinfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        session = createSession(new Course("ENGL", "101"), startDate);;
    }
    protected abstract Session createSession(Course course, LocalDate date);
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
        var sessionC = createSession(new Course("CMSC", "200"), startDate);
        var sessionE = createSession(new Course("ENGL", "101"), startDate);
        var sessionE2 = createSession(new Course("ENGL", "201"), startDate);
        assertEquals(0, session.compareTo(sessionE));

        assertTrue(session.compareTo(sessionC) > 0);
        assertTrue(sessionC.compareTo(session) < 0);
        assertTrue(session.compareTo(sessionE2) < 0);
    }
    @Test
    void getSessionLength() {
        assertThat(session.getSessionLength(), greaterThan(0));
    }

    @Test
    void averageGpaForPartTimeStudents(){
        record StudentInfo(Grade grade, int credit){}
        final StudentInfo[] studentInfos = {
            new StudentInfo(Grade.A, 2),
            new StudentInfo(Grade.B, 2),
            new StudentInfo(Grade.C, Student.CREDITS_REQUIRED_FOR_FULL_TIME),
        };
        for(final var info : studentInfos){
            final var student = new Student("a");
            student.addGrade(info.grade);
            student.addCredits(info.credit);
            session.enroll(student);
        }
        assertEquals(3.5, session.averageGpaForPartTineStudents());
    }
    @Test
    void testIterate() {
        session.enroll(new Student("1"));
        session.enroll(new Student("2"));
        session.enroll(new Student("3"));
        List<Student> result = new ArrayList<>();
        for( Student student : session) result.add(student);
        assertEquals(session.getAllStudents(), result);
    }
    @Test
    void sessionUrl() {
        final String url =  "http://course.langrsoft.com/cmsc300";

        try {
            session.setUrl(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        assertEquals(url, session.getUrl().toString());
    }
}
