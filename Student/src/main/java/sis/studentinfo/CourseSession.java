package sis.studentinfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A representation for a single session of a specific university course.
 */
public class CourseSession extends Session{
    // auto clean start
    private  static int count;

    public CourseSession(String department, String number, LocalDate startDate) {
        super(department, number, startDate);
        ++CourseSession.count;
    }

    public static void resetCount(int count) {CourseSession.count = count;}
    public static int getCount() {
        return CourseSession.count;
    }

    @Override
    protected int getSessionLength() {
        return 16;
    }

}
