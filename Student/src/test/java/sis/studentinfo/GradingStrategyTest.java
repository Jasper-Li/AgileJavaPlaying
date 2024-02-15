package sis.studentinfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradingStrategyTest {

    @Test
    void getGradeFor() {
        record GradePointChecks(GradingStrategy strategy, GradePointsCheck[] checks){}
        GradePointsCheck[] gradePointsHonours  = {
                new GradePointsCheck(Grade.A, 5),
                new GradePointsCheck(Grade.B, 4),
                new GradePointsCheck(Grade.C, 3),
                new GradePointsCheck(Grade.D, 2),
                new GradePointsCheck(Grade.F, 0)
        };
        GradePointsCheck[] gradePointsElite  = {
                new GradePointsCheck(Grade.A, 4),
                new GradePointsCheck(Grade.B, 4),
                new GradePointsCheck(Grade.C, 4),
                new GradePointsCheck(Grade.D, 4),
                new GradePointsCheck(Grade.F, 3)
        };
        GradePointsCheck[] gradePointsRegular  = {
                new GradePointsCheck(Grade.A, 4),
                new GradePointsCheck(Grade.B, 3),
                new GradePointsCheck(Grade.C, 2),
                new GradePointsCheck(Grade.D, 1),
                new GradePointsCheck(Grade.F, 0)
        };
        // Array list for each
        GradePointChecks[] checks = {
            new GradePointChecks(new GradingStrategyRegular(), gradePointsRegular),
            new GradePointChecks(new GradingStrategyElite(), gradePointsElite),
            new GradePointChecks(new GradingStrategyHonours(), gradePointsHonours)
        };
        for (var entry: checks) {
            var strategy = entry.strategy();
            var gradePointsChecks = entry.checks();
            for (var check : gradePointsChecks) {
                var grade =check.grade();
                var gradePointExpected = check.gradePointExpected();
                assertEquals(gradePointExpected, strategy.getGradePointsFor(grade));
            }
        }
    }
}