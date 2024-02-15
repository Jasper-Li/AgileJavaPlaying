package sis.report;

import sis.studentinfo.CourseSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sis.report.RosterReport.NEW_LINE;

public class CourseReport {
    private final List<CourseSession> courseSessions;
    public CourseReport(List<CourseSession> courseSessions) {
        this.courseSessions = courseSessions;
    }

    public String text() {
        Collections.sort(courseSessions);
        var buffer = new StringBuilder();
        for(var courseSession : courseSessions) {
            buffer.append(courseSession.getDepartment())
                .append(" ")
                .append(courseSession.getNumber())
                .append(NEW_LINE);
        }
        return buffer.toString();
    }
}
