package sis.studentinfo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;

public abstract class Session implements Comparable<Session>, Iterable<Student> {
    private final String department;
    private final String number;
    private int credits;
    private final List<Student> students = new ArrayList<Student>();
    private final LocalDate startDate;

    private URL url;

    public Session(String department, String number, LocalDate startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public int compareTo(Session o) {
        var result = department.compareTo(o.department);
        if (result != 0) return result;
        return number.compareTo(o.number);
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
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
}
