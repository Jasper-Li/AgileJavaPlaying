package sis.studentinfo;

public class GradingStrategyRegular implements  GradingStrategy{
    public int getGradePointsFor(Grade grade) {
        return grade.getPoints();
    }
}
