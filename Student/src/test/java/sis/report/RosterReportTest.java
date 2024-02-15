package sis.report;

import org.junit.jupiter.api.Test;
import sis.report.RosterReport;
import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sis.report.RosterReport.NEW_LINE;

public class RosterReportTest {
    @Test
    void getReport() {
        var startDate = LocalDate.of(2003, 1, 6);
        var session = new CourseSession("ENGL", "101", startDate);
        session.enroll(new Student("A"));
        session.enroll(new Student("B"));

        var rosterReport = new RosterReport(session);
        final var  report = rosterReport.getReport();
// System.out.println(report);
        final String rosterReportExpect = STR."""
                \{RosterReport.ROSTER_REPORT_HEADER}\
                A\{NEW_LINE}\
                B\{NEW_LINE}\
                \{RosterReport.ROSTER_REPORT_FOOTER}\
                2\{NEW_LINE}\
                """;

        assertEquals(rosterReportExpect, report);
    }
}
