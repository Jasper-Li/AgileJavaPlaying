package sis.studentinfo;

public enum Grade {
    A(4),
    B(3),
    C(2),
    D(1),
    F(0);

    private int points;
    Grade(int points) {
        this.points = points;
    }

    int getPoints() {
        return points;
    }
}
