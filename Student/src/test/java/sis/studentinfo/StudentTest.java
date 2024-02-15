package sis.studentinfo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {
    private static final String defaultName = "James Bang";
    private static final double GPA_TOLERANCE = 0.05;
    private Student student;

    record GpaCheck(Grade grade, double gpaExpected) {
    }
    @BeforeEach
    void setUp() {
        student = new Student(defaultName);
    }

    @Test
    void calculateGpaAccumulation()
    {
        GpaCheck[] gpaCheckRegular = {
            new GpaCheck(Grade.A, 4.0),
            new GpaCheck(Grade.B, 3.5),
            new GpaCheck(Grade.C, 3.0),
            new GpaCheck(Grade.D, 2.5),
            new GpaCheck(Grade.F, 2.0),
        };
        GpaCheck[] gpaCheckHonours= {
                new GpaCheck(Grade.A, 5.0),
                new GpaCheck(Grade.B, 4.5),
                new GpaCheck(Grade.C, 4.0),
                new GpaCheck(Grade.D, 3.5),
                new GpaCheck(Grade.F, 2.8),
        };
        GpaCheck[] gpaCheckElite= {
                new GpaCheck(Grade.A, 4.0),
                new GpaCheck(Grade.B, 4.0),
                new GpaCheck(Grade.C, 4.0),
                new GpaCheck(Grade.D, 4.0),
                new GpaCheck(Grade.F, 3.8),
        };
        checkGpa(gpaCheckRegular);
        student.setStrategy(new GradingStrategyHonours());
        checkGpa(gpaCheckHonours);
        student.setStrategy(new GradingStrategyElite());
        checkGpa(gpaCheckElite);
    }
    private void checkGpa(GpaCheck[] checks) {
        student.clearGrade();
        assertEquals(student.getGpa(), 0.0, GPA_TOLERANCE);
        for (var v : checks) {
            student.addGrade(v.grade());
            assertEquals(v.gpaExpected(), student.getGpa());
        }

    }
    private static GradePointsCheck[] getGradePointCheckerBy(StudentType type) {
        GradePointsCheck[] honoursGradePoints  = {
                new GradePointsCheck(Grade.A, 5),
                new GradePointsCheck(Grade.B, 4),
                new GradePointsCheck(Grade.C, 3),
                new GradePointsCheck(Grade.D, 2),
                new GradePointsCheck(Grade.F, 0)
        };
        GradePointsCheck[] eliteGradePoints  = {
                new GradePointsCheck(Grade.A, 4),
                new GradePointsCheck(Grade.B, 4),
                new GradePointsCheck(Grade.C, 4),
                new GradePointsCheck(Grade.D, 4),
                new GradePointsCheck(Grade.F, 3)
        };
        GradePointsCheck[] regularGradePoints  = {
                new GradePointsCheck(Grade.A, 4),
                new GradePointsCheck(Grade.B, 3),
                new GradePointsCheck(Grade.C, 2),
                new GradePointsCheck(Grade.D, 1),
                new GradePointsCheck(Grade.F, 0)
        };
        return switch (type) {
            case StudentType.Honours -> honoursGradePoints;
            case StudentType.Elite-> eliteGradePoints;
            default ->  regularGradePoints;
        };
    }
    @Test
    void create() {
        assertEquals(defaultName, student.getName());

        final String name1 = "Jane Doe";
        Student student1 = new Student(name1);
        assertEquals(name1, student1.getName());

        final String name2 = "Joe Blown";
        Student student2 = new Student(name2);
        assertEquals(name2, student2.getName());

        assertEquals(name1, student1.getName());
    }
    @Test
    void studentStatus() {
        //var student = new Student("a");
        assertEquals(0, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(3);
        assertEquals(3, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(4);
        assertEquals(7, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(5);
        assertEquals(12, student.getCredits());
        assertEquals(Student.CREDITS_REQUIRED_FOR_FULLTIME, student.getCredits());
        assertTrue(student.isFullTime());
    }
    @Test
    void inState() {
        assertFalse(student.isInState());
        student.setState(Student.STATE_CO);
        assertTrue(student.isInState());
        student.setState("CA");
        assertFalse(student.isInState());
        student.setState("co");
        assertTrue(student.isInState());
    }
}
/*
    @Test
    void printGrade() {
        System.out.println(Grade.A);
    }
    @Test
    void gradePointFor() {
        checkGradePoint(StudentType.Honours);
        checkGradePoint(StudentType.Elite);

    }
    private void checkGradePoint(StudentType type) {
        var gradePoints = getGradePointCheckerBy(type);
        for (var v: gradePoints) {
            assertEquals(v.gradePointExpected(), Student.gradePointFor(v.grade(), type));
        }

    }
    @Test
    void getGradePointRegular() {
        for (var v: getGradePointCheckerBy(StudentType.Regular)) {
            assertEquals(v.gradePointExpected(), Student.gradePointFor(v.grade()));
        }
    }
     */
