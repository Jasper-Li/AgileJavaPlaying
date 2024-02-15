package sis.report;

import org.junit.jupiter.api.Test;
import sis.studentinfo.Grade;

import java.util.spi.ResourceBundleProvider;

import static org.junit.jupiter.api.Assertions.*;

class ReportCardTest {
    record CheckMessage(Grade grade, String message){}
    @Test
    void getMessage() {
        String[] messages = {
            ReportCard.A_MSG,
            ReportCard.B_MSG,
            ReportCard.C_MSG,
            ReportCard.D_MSG,
            ReportCard.F_MSG,
        };
        for (var msg : messages) {
            assertNotEquals(0, msg.length());
        }

        var card = new ReportCard();
        CheckMessage[] checks = {
                new CheckMessage(Grade.A, ReportCard.A_MSG),
                new CheckMessage(Grade.B, ReportCard.B_MSG),
                new CheckMessage(Grade.C, ReportCard.C_MSG),
                new CheckMessage(Grade.D, ReportCard.D_MSG),
                new CheckMessage(Grade.F, ReportCard.F_MSG),
        };
        for (var check : checks) {
           assertEquals(check.message, card.getMessage(check.grade));
        }

    }
}