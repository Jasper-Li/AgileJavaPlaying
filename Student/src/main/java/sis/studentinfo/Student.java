package sis.studentinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Student {
    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String STATE_CO = "CO";
    private final String fullName;
    private final String firstName;
    private final String middleName;
    private final String lastName ;
    private int credits;
    private String state = "";
    private final List<Grade> grades = new ArrayList<Grade>();
    private GradingStrategy  strategy = new GradingStrategyRegular();
    private List<Integer> charges = new ArrayList<>();

    public Student(String fullName) {
        this.fullName = fullName;
        List<String> names = split(fullName);
        // names.size = [1..3]
        lastName = names.removeLast();
        if(!names.isEmpty()){
            firstName = names.removeFirst();
            if(!names.isEmpty()){
                middleName = names.getFirst();
            } else {
                middleName = "";
            }
        } else {
            firstName = "";
            middleName = "";
        }
    }
    public static List<String> split(String fullName){
        return new ArrayList<>(Arrays.asList(fullName.split(" ")));
    }

    public String getFullName() {
        return fullName;
    }
    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }


    public int getCredits() {
        return credits;
    }

    public boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
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

    public String getFirstName() {
        return firstName;
    }

    public void addCharge(int charge) {
        charges.add(charge);
    }

    public int totalCharges() {
        int sum = 0;
        for(int charge: charges) sum += charge;
        return sum;
    }
}
