package org.example;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class CountingLogHandlerTest {
    @Test
    void basic() {
        var logger = Logger.getLogger("basic");
        logger.setLevel(Level.ALL);
        var handler = new CountingLogHandler();
        logger.addHandler(handler);
        logger.info("");
        logger.info("");
        logger.warning("");
        logger.severe("");
        logger.severe("");
        logger.severe("");
        logger.fine("");

        Map<Level, Integer> countsExpected = Map.of(
            Level.INFO, 2,
            Level.WARNING, 1,
            Level.SEVERE, 3,
            Level.FINE, 1
        );

        assertEquals(countsExpected, handler.counts);
    }
    @Test
    void setFormatter() {
        var logger = Logger.getLogger("setFormatter");
        var basicFormater = new Formatter() {
            @Override
            public String format(LogRecord record) {
                return  STR."\{record.getLevel()}: \{record.getMessage()}";
            }
        };
        for (var handler : logger.getHandlers()) {
            handler.setFormatter(basicFormater);
        }
        var handler = new CountingLogHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                var level = record.getLevel();
                return  STR."\{level}: \{record.getMessage()}(\{level} total = \{handler.getCount(level)})";
            }
        });
        logger.addHandler(handler);

        logger.warning("Watch out.");

    }
}