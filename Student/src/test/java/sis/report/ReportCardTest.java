package sis.report;

import org.junit.jupiter.api.Test;
import sis.studentinfo.Grade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.spi.ResourceBundleProvider;

import static org.junit.jupiter.api.Assertions.*;
import static sis.report.ReportCard.A_MSG;

class ReportCardTest {
    record CheckMessage(Grade grade, String message){}
    @Test
    void getMessage() {
        String[] messages = {
            A_MSG,
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
                new CheckMessage(Grade.A, A_MSG),
                new CheckMessage(Grade.B, ReportCard.B_MSG),
                new CheckMessage(Grade.C, ReportCard.C_MSG),
                new CheckMessage(Grade.D, ReportCard.D_MSG),
                new CheckMessage(Grade.F, ReportCard.F_MSG),
        };
        for (var check : checks) {
           assertEquals(check.message, card.getMessage(check.grade));
        }
    }
    @Test
    void testKeys() {
        Set<Grade> expectedKeys = Set.of(
                Grade.A, Grade.B,Grade.C, Grade.D, Grade.F
        );
        var grades = new ReportCard().getMessages().keySet();
        assertEquals(expectedKeys, grades);
    }

    @Test
    void testValues() {
        List<String> expectedMessages = List.of(
            A_MSG,
            ReportCard.B_MSG,
            ReportCard.C_MSG,
            ReportCard.D_MSG,
            ReportCard.F_MSG
        );
        List<String> values = new ArrayList<> (new ReportCard().getMessages().values());
        assertEquals(expectedMessages, values);

    }

    @Test
    void testEntries() {
        record Entry(Grade grade, String message){}
        Set<Entry> expectedEntries = Set.of(
            new Entry(Grade.A, ReportCard.A_MSG),
            new Entry(Grade.B, ReportCard.B_MSG),
            new Entry(Grade.C, ReportCard.C_MSG),
            new Entry(Grade.D, ReportCard.D_MSG),
            new Entry(Grade.F, ReportCard.F_MSG)
        );
        Set<Entry> entries = new HashSet<>();
        var entrySet = new ReportCard().getMessages().entrySet();
        for (var entry : entrySet) {
            entries.add(new Entry(entry.getKey(), entry.getValue()));
        }
        assertEquals(expectedEntries, entries);

    }
}