package sis.report;

import sis.studentinfo.Grade;

import java.util.EnumMap;
import java.util.Map;

public class ReportCard {
    public static final String A_MSG = "Excellent";
    public static final String B_MSG = "Very good";
    public static final String C_MSG = "Hmm...";
    public static final String D_MSG = "You are not trying";
    public static final String F_MSG = "Loser";
    EnumMap<Grade, String> messages = null;

    String getMessage(Grade grade) {
        if(messages == null) {
            messages = new EnumMap<>(Map.of(
                    Grade.A, A_MSG,
                    Grade.B, B_MSG,
                    Grade.C, C_MSG,
                    Grade.D, D_MSG,
                    Grade.F, F_MSG
            ));
        }
        return messages.get(grade);
    }
}
