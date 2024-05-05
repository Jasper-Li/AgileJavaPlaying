package sis.studentinfo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CourseCatalog {
    List<CourseSession> sessions = new ArrayList<>();
    public void add(CourseSession courseSession) {
        sessions.add(courseSession);
    }

    public void store(String filename) throws IOException {

        ObjectOutputStream output = null;
        try {
            output = new ObjectOutputStream(new FileOutputStream(filename));
            output.writeObject(sessions);
        } finally {
            if(output != null) output.close();
        }
    }

    public void clearAll() {
        sessions.clear();
    }

    public List<CourseSession> getSessions() {
        return sessions;
    }

    public void load(String filename) throws IOException {
        ObjectInputStream input = null;
        try{
            input = new ObjectInputStream(new FileInputStream(filename));
            List<CourseSession> buf = (List<CourseSession>)input.readObject();
            sessions.addAll(buf);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(CourseCatalog.class.getName()).warning("failed to read.");
        } finally {
            if(input != null) input.close();
        }
    }
}
