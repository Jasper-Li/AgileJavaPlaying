package sis.studentinfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sis.studentinfo.Grade.A;

class GradeTest {

    @Test
    void getPoints() {
        Grade a = A;
        record Check(Grade grade, int points){};
        Check[] checks= {
                new Check(A, 4),
                new Check(Grade.B, 3),
                new Check(Grade.C, 2),
                new Check(Grade.D, 1),
                new Check(Grade.F, 0),
        };
        for(var check  : checks ) {
            assertEquals(check.points, check.grade.getPoints());
        }
    }
}