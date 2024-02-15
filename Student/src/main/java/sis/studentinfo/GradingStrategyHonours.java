package sis.studentinfo;

public class GradingStrategyHonours extends GradingStrategyRegular {
    @Override
    public int getGradePointsFor(Grade grade) {
        var basicGradePoints = super.getGradePointsFor(grade);
        return basicGradePoints > 0 ? basicGradePoints + 1 : 0;
    }
}
