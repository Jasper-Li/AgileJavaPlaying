package sis.summer;

import sis.studentinfo.Course;
import sis.studentinfo.CourseSession;
import sis.studentinfo.Session;

import java.time.LocalDate;

public class SummerCourseSession  extends Session {
    public SummerCourseSession(Course course, LocalDate startDate){
        super(course, startDate);
    }

    @Override
    protected int getSessionLength() {
        return 8;
    }
}
