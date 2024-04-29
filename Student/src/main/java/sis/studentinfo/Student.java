package sis.studentinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


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
    public final static int MAX_NAME_PARTS = 3;
    private final static Logger logger = Logger.getLogger(Student.class.getName());

    public Student(String fullName) {
        this.fullName = fullName;
        List<String> names = split(fullName);
        if(names.size() > MAX_NAME_PARTS) {
            var msg = STR."Student name '\{fullName}' contains more than \{MAX_NAME_PARTS} parts.";
            // log message here
            log(msg);
            throw new StudentNameFormatException(msg);
        }
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

    private void log(String msg) {
        logger.info(msg);
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
        logger.fine(STR."begin getGPa: \{System.currentTimeMillis()}");
        double result;
        if(grades.isEmpty()) {
            result = 0;
        } else {

            double sum = 0.0;
            for (var grade : grades) {
                sum += strategy.getGradePointsFor(grade);
            }
            result = sum / grades.size();
        }
        logger.fine(STR."end getGPa: \{System.currentTimeMillis()}");
        return result;
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
