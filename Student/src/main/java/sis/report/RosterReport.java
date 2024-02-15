package sis.report;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StringTemplate.STR;

class RosterReport {
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String ROSTER_REPORT_HEADER = STR."Student\{NEW_LINE}----\{NEW_LINE}";
    public static final String ROSTER_REPORT_FOOTER = STR."\{NEW_LINE}# student = ";
    private CourseSession session;
    public RosterReport(CourseSession session) {
        this.session = session;
    }

    String getReport() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(ROSTER_REPORT_HEADER);
        List<Student> students = session.getAllStudents();
        for (var student: students) {
            buffer.append(student.getName())
                  .append(NEW_LINE);
        }
        buffer.append(ROSTER_REPORT_FOOTER)
            .append(students.size())
            .append(NEW_LINE);
        return buffer.toString();
    }
}
