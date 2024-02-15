package sis.summer;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Session;

import java.time.LocalDate;

public class SummerCourseSession  extends Session {
    public SummerCourseSession(String department, String number, LocalDate startDate){
        super(department, number, startDate);
    }

    @Override
    protected int getSessionLength() {
        return 8;
    }
}
