package org.example;

import java.util.Calendar;
import java.util.logging.Logger;

public class Chapter8 {
    public static final String blowsUpMessage = "Somebody should catch this!";
    void blowsUp() {
        throw new SimpleException(blowsUpMessage);
    }
    void callBlowsUp() {
        blowsUp();
    }

    public void rethrows() {
        try{
            blowsUp();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(Exception e) {
        var logger = Logger.getLogger(Chapter8.class.getName());
        var stacks = e.getStackTrace();
        for(int i = stacks.length-1; i>=0; --i) {
            logger.info(stacks[i].toString());
        }
    }
}
