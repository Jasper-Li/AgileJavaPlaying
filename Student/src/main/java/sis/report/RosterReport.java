package sis.report;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static java.lang.StringTemplate.STR;
import static java.util.FormatProcessor.FMT;

class RosterReport {
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String ROSTER_REPORT_HEADER = STR."Student\{NEW_LINE}----\{NEW_LINE}";
    public static final String ROSTER_REPORT_FOOTER = STR."\{NEW_LINE}# students = ";
    private CourseSession session;
    public RosterReport(CourseSession session) {
        this.session = session;
    }


    public void writeReport(Writer writer) throws IOException {
        writer.write(ROSTER_REPORT_HEADER);
        for(var student: session.getAllStudents()) {
            writer.write(FMT."\{student.getFullName()}%n");
        }
        writer.write(FMT."\{ROSTER_REPORT_FOOTER}\{session.getAllStudents().size()}%n");
    }

    public void writeReport(String filename) throws IOException {
        Writer writer = new BufferedWriter(new FileWriter(filename));
        try {
            writeReport(writer);
        } finally {
            writer.close();
        }
    }
}
