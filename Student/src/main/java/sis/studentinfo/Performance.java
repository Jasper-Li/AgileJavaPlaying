package sis.studentinfo;

public class Performance {
    int[] scores = {};
    public void setNumberOfTests(int i) {
        scores = new int[i];
    }

    public void set(int index, int value) {
        scores[index] = value;
    }

    public int get(int i) {
        return  scores[i];
    }

    public double average() {
        int sum = 0;
        for(var score:scores) {
            sum += score;
        }
        return (double)sum/scores.length;
    }

    public void setScores(int... scores ) {
        this.scores = scores;
    }
}
