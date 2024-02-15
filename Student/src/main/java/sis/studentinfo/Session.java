package sis.studentinfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Session implements Comparable<Session> {
    private final String department;
    private final String number;
    private int credits;
    private final List<Student> students = new ArrayList<Student>();
    private final LocalDate startDate;

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
    /**
     * @param credits should be positive.
     */
}
