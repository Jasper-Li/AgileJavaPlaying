package sis.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sis.studentinfo.Course;
import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;

import java.io.*;
import java.time.LocalDate;

import static java.util.FormatProcessor.FMT;
import static org.junit.jupiter.api.Assertions.*;

public class RosterReportTest {
    private RosterReport report = null;
    private static final String EXPECTED_ROSTER_REPORT_STRING = FMT."""
                \{RosterReport.ROSTER_REPORT_HEADER}\
                A%n\
                B%n\
                \{RosterReport.ROSTER_REPORT_FOOTER}\
                2%n\
                """;
    @BeforeEach
    void setUp() {
        var startDate = LocalDate.of(2003, 1, 6);
        var session = new CourseSession(new Course("ENGL", "101"), startDate);
        session.enroll(new Student("A"));
        session.enroll(new Student("B"));
        this.report = new RosterReport(session);
    }

    @Test
    void writeReportToWriter() {
        Writer writer = new StringWriter();
        try {
            report.writeReport(writer);
        } catch (IOException e) {
            fail(STR."Failed to write report with exception: \{e}");
        }
        assertEquals(EXPECTED_ROSTER_REPORT_STRING, writer.toString());
    }
    @Test
    void writeReportToFile() throws FileNotFoundException, IOException {
        final String filename = "testFileReport.txt";
        try {
            report.writeReport(filename);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            var builder = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(FMT."\{line}%n");
            }
            bufferedReader.close();
            assertEquals(EXPECTED_ROSTER_REPORT_STRING, builder.toString());

        }finally {
            File file = new File(filename);
            if(file.exists()) {
                assertTrue(file.delete(), STR."Failed to delete \{filename}");
            }
        }

    }
}
