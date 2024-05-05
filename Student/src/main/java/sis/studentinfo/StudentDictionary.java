package sis.studentinfo;

import sis.db.DataBase;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StudentDictionary {
    private DataBase db;

    public StudentDictionary(String filenameBase) throws IOException {
        db = new DataBase(filenameBase);
    }

    public void add(Student student) throws IOException {
        db.add(student.getId(), student);
    }
    int size() {
        return db.count();
    }

    public Student findById(String id) throws IOException {
        return (Student)db.find(id);
    }

    public void remove() throws IOException {
        db.remove();
    }

    public boolean fileExists() {
        return db.fileExists();
    }

    public void close() throws IOException {
        db.close();
    }
}
