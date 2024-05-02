package sis.studentinfo;

import java.util.HashMap;
import java.util.Map;

public class StudentDictionary {
    Map<String, Student> map = new HashMap<>();

    public void add(Student student) {
        map.put(student.getId(), student);
    }

    public Student findById(String id) {
        return map.get(id);
    }
}
