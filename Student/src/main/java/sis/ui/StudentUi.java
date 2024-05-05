package sis.ui;

import sis.studentinfo.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.FormatProcessor.FMT;

public class StudentUi {
    public static final String MENU = "(A)dd or (Q)uit?";
    public static final String ADD_OPTION = "A";
    public static final String QUIT_OPTION = "Q";
    public static final String NAME_PROMPT = "Name: ";
    public static final String ADDED_MSG = "Added.";

    private BufferedReader reader;
    private BufferedWriter writer;
    private List<Student> students = new ArrayList<>();
    public StudentUi(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public StudentUi() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void run() throws IOException {
        String line;
        do {
            write(MENU);

            line = reader.readLine();
            if (line.equals(ADD_OPTION)) {
                write(NAME_PROMPT);

                String name = reader.readLine();
                students.add(new Student(name));

                write(FMT."\{ADDED_MSG}%n");
            }
        } while(!line.equals(QUIT_OPTION));
    }
    private void write(String msg) throws IOException {
        writer.write(msg);
        writer.flush();

    }

    public List<Student> getStudents() {
        return students;
    }

    public static void main(String[] args) throws IOException {
        StudentUi studentUi = new StudentUi();
        studentUi.run();
    }
}
