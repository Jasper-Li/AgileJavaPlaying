/*
package sis.studentinfo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentDb {
    static StudentDictionary DATABASE;

    static {
        try {
            DATABASE = new StudentDictionary();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void remove() throws IOException {
        DATABASE.close();
        DATABASE.remove();
    }
    public static void add(Student student) throws IOException {
        var id = DATABASE.size();
        student.setId(String.valueOf(id));
        DATABASE.add(student);
    }
    public static Student findById(String id) throws IOException {
        return DATABASE.findById(id);
    }

    public static int size() {
        return DATABASE.size();
    }
}
 */
