package sis.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sis.studentinfo.Student;

import java.io.*;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

import static java.util.FormatProcessor.FMT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentUiTest {
    private List<String> names = List.of(
        "Leo Xerces Schmoo",
        "Jim Green"
    );

    private StringBuilder inputBuilder;
    private StringBuilder expectedOutputBuilder;
    private BufferedWriter writer;
    private BufferedReader reader;
    private InputStream inputstream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        inputBuilder = new StringBuilder();
        expectedOutputBuilder = new StringBuilder();
        setUp(expectedOutputBuilder, inputBuilder);

        inputstream = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        reader = new BufferedReader(
            new InputStreamReader(inputstream)
        );
        outputStream = new ByteArrayOutputStream();
        writer = new BufferedWriter(
            new OutputStreamWriter(outputStream)
        );

    }
    @Test
    void createStudent() throws IOException {
        var studentUi = new StudentUi(reader, writer);
        studentUi.run();
        assertEquals(expectedOutputBuilder.toString(), outputStream.toString());
        assertStudents(studentUi.getStudents());
    }

    @Test
    void createStudentByStdInput() throws IOException {
        var consoleIn = System.in;
        var consoleOut = System.out;
        System.setIn(inputstream);
        System.setOut(new PrintStream(outputStream));
        try{
            StudentUi studentUi = new StudentUi();
            studentUi.run();
            assertEquals(expectedOutputBuilder.toString(), outputStream.toString());
            assertStudents(studentUi.getStudents());

        }finally {
            System.setIn(consoleIn);
            System.setOut(consoleOut);
        }
    }

    private void assertStudents(List<Student> students){
        assertEquals(names.size(), students.size());
        for(int i =0 ;i< names.size(); ++i) {
            assertEquals(names.get(i), students.get(i).getFullName());
        }
    }

    private void setUp(StringBuilder expectedOutputBuilder, StringBuilder inputBuilder) {
        for (var name : names) {
            expectedOutputBuilder.append(StudentUi.MENU);
            inputBuilder.append(line(StudentUi.ADD_OPTION));
            expectedOutputBuilder.append(StudentUi.NAME_PROMPT);
            inputBuilder.append(line(name));
            expectedOutputBuilder.append(line(StudentUi.ADDED_MSG));
        }
        expectedOutputBuilder.append(StudentUi.MENU);
        inputBuilder.append(line(StudentUi.QUIT_OPTION));

    }
    private String line(String msg) {
        return FMT."\{msg}%n";
    }
}
