package sis.report;

import org.junit.jupiter.api.Test;
import sis.studentinfo.Course;
import sis.studentinfo.CourseSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sis.report.RosterReport.NEW_LINE;

public class CourseReportTest {
    @Test
    void text() {
        var today = LocalDate.now();
        var courseSessions = new ArrayList<CourseSession>(List.of(
                new CourseSession(new Course("ENGL", "101"), today),
                new CourseSession(new Course("CZEC", "200"), today),
                new CourseSession(new Course("ITAL", "410"), today),
                new CourseSession(new Course("CMSC", "101"), today),
                new CourseSession(new Course("CZEC", "220"), today),
                new CourseSession(new Course("ITAL", "330"), today)
        ));
        var textShould =
            "CMSC 101" + NEW_LINE +
            "CZEC 200" + NEW_LINE +
            "CZEC 220" + NEW_LINE +
            "ENGL 101" + NEW_LINE +
            "ITAL 330" + NEW_LINE +
            "ITAL 410" + NEW_LINE;
        var courseReport = new CourseReport(courseSessions);
        assertEquals(textShould, courseReport.text());
    }
}
