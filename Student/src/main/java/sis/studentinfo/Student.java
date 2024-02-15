package sis.studentinfo;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public static final int CREDITS_REQUIRED_FOR_FULLTIME = 12;
    public static final String STATE_CO = "CO";
    private final String name;
    private int credits;
    private String state = "";
    private final List<Grade> grades = new ArrayList<Grade>();
    private GradingStrategy  strategy = new GradingStrategyRegular();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULLTIME;
    }

    /**
     * @param credits should be positive.
     */
    public void addCredits(int credits) {
        this.credits += credits;
    }

    public boolean isInState() {
        return  this.state.equalsIgnoreCase(STATE_CO);
    }

    public void setState(String state) {
        this.state =state;
    }
    public void setStrategy(GradingStrategy strategy) {
        this.strategy = strategy;
    }

    public double getGpa() {
        if(grades.isEmpty())
            return 0;
        double sum = 0.0;
        for (var grade : grades) {
            sum += strategy.getGradePointsFor(grade);
        }
        return sum/grades.size();
    }


    public void addGrade(Grade grade) {
        grades.add(grade);
    }
    public void clearGrade(){
        grades.clear();
    }
}
