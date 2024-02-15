package sis.studentinfo;

public class GradingStrategyElite implements  GradingStrategy{
    @Override
    public int getGradePointsFor(Grade grade) {
        return switch (grade) {
            case Grade.A, Grade.B, Grade.C, Grade.D -> 4;
            default ->  3;
        };
    }
}
