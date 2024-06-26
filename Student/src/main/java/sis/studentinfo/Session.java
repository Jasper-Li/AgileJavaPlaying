package sis.studentinfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;

public abstract class Session implements Comparable<Session>, Iterable<Student>, Serializable {
    private final Course course;
    private int credits;
    private transient List<Student> students = new ArrayList<Student>();
    private final LocalDate startDate;

    private URL url;

    public Session(Course course, LocalDate startDate) {
        this.course = course;
        this.startDate = startDate;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

    // TODO: use course.
    @Override
    public int compareTo(Session o) {
        var result = getDepartment().compareTo(o.getDepartment());
        if (result != 0) return result;
        return getNumber().compareTo(o.getNumber());
    }

    public String getDepartment() {
        return course.getDepartment();
    }

    public String getNumber() {
        return course.getNumber();
    }
    protected LocalDate getStartDate() {
        return startDate;
    }
    protected abstract int getSessionLength();

    public LocalDate getEndDate() {
        final int weeks = getSessionLength();
        final int daysFromFridayToMonday = 3;
        final int numberOfDays = weeks * 7 - daysFromFridayToMonday;
        return startDate.plusDays(numberOfDays);
    }
    int getNumberOfStudent() {
            return students.size();
        }

    Student getStudentBy(int index) {
        return students.get(index);
    }
    public List<Student> getAllStudents() {
        return students;
    }

    public void enroll(Student student) {
        student.addCredits(this.credits);
        students.add(student);
    }

    public double averageGpaForPartTineStudents() {
        double sum = 0.0;
        int count = 0;
        for(final var student : students){
            if(!student.isFullTime()) {
                final var gpa = student.getGpa();
//                out.println(STR."add gpa: \{gpa}");
                sum += gpa;
                ++count;
            }
        }
        return sum/count;
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }

    /**
     * @param credits should be positive.
     */

    public URL getUrl() {
        return url;
    }

    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public int getCredits() {
        return credits;
    }

    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        output.writeInt(students.size());
        for(var student : students) {
            output.writeUTF(student.getFullName());
        }
    }
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        int size = input.readInt();
        students = new ArrayList<>(size);
        for(int i=0; i<size; ++i) {
            String fullName = input.readUTF();
            var student =fakeFindByFullName(fullName);
            students.add(student);
        }
    }
    private static Student fakeFindByFullName(String fullName) {
        return new Student(fullName);

    }
}
