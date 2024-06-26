package sis.studentinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.logging.Logger;


public class Student implements Serializable {
    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String STATE_CO = "CO";
    public final static int MAX_NAME_PARTS = 3;
    private final static Logger logger = Logger.getLogger(Student.class.getName());

    private final String fullName;
    private String id=null;
    private int credits;
    private String state = "";
    private final List<Grade> grades = new ArrayList<Grade>();
    private GradingStrategy  strategy = new GradingStrategyRegular();
    private List<Integer> charges = new ArrayList<>();

    private BitSet flags = new BitSet(Flag.values().length);

    public Student(String fullName) {
        this.fullName = fullName;
        List<String> names = split(fullName);
        if(names.size() > MAX_NAME_PARTS) {
            var msg = STR."Student name '\{fullName}' contains more than \{MAX_NAME_PARTS} parts.";
            logger.info(msg);
            throw new StudentNameFormatException(msg);
        }
    }

    public static List<String> split(String fullName){
        return new ArrayList<>(Arrays.asList(fullName.split(" ")));
    }

    public String getFullName() {
        return fullName;
    }
    public String getFirstName() {
        var names = split(fullName);
        names.removeLast();
        if(names.isEmpty()) return "";
        else return names.getFirst();
    }
    public String getMiddleName() {
        var names = split(fullName);
        names.removeLast();
        if(names.isEmpty()) return "";
        names.removeFirst();
        if(names.isEmpty()) return "";
        else return names.getFirst();
    }

    public String getLastName() {
        return split(fullName).removeLast();
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


    public void addCharge(int charge) {
        charges.add(charge);
    }

    public int totalCharges() {
        int sum = 0;
        for(int charge: charges) sum += charge;
        return sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void set(Flag... flags){
        for (var flag: flags) {
            this.flags.set(flag.getValue());
        }
    }

    public boolean isOn(Flag flag) {
        return this.flags.get(flag.getValue());
    }

    public boolean isOff(Flag flag) {
        return !isOn(flag);
    }

    public void unset(Flag flag) {
        flags.clear(flag.getValue());
    }

    public enum Flag {
        ON_CAMPUS(0),
        TAX_EXEMPT(1),
        MINOR(2),
        TROUBLEMAKER(3),
        ;

        private int value;
        Flag(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}
