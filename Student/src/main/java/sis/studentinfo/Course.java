package sis.studentinfo;

public class Course {
    private final String department;
    private final String number;
    public Course(String department, String number) {
        this.department = department;
        this.number = number;
    }
    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Course other) {
            return other.department.equals(department) &&
                    other.number.equals(number);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = department.hashCode();
        return result * 41 + number.hashCode();
    }
}
