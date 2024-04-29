package org.example;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static java.lang.System.out;

public class CountingLogHandler  extends ConsoleHandler {

    public Map<Level, Integer> counts = new HashMap<>();
    @Override
    public void publish(LogRecord record) {
        var level = record.getLevel();
        var count = getCount(level);
        counts.put(level, count+1);
    }

    public int getCount(Level level) {
        return counts.getOrDefault(level, 0);
    }

    @Override
    public void flush() {

    }
    @Override
    public void close() throws SecurityException {

    }
}
