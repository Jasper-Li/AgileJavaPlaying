package sis.studentinfo;

import java.io.Serializable;

public interface GradingStrategy extends Serializable {
    int getGradePointsFor(Grade grade);
}
