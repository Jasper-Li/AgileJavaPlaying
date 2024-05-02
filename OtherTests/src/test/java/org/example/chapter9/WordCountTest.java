package org.example.chapter9;

import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordCountTest {

    @Test
    void split() {
        record Check(String data, List<String> elements){}
        Check[] checks = new Check[] {
            new Check("a b c", List.of("a", "b", "c")),
            new Check("a b, c", List.of("a", "b", "c")),
            new Check("a b c,", List.of("a", "b", "c")),
            new Check("aA bB cC", List.of("aA", "bB", "cC")),
        };
        for(var check: checks) {
            final var result = WordCount.split(check.data);
            assertEquals(check.elements, result, STR."\{check.elements} != \{result}");
        }
    }

    @Test
    void init() {
        final var string = """
                Create a String literal using the first two sentences of this exercise.
                You will create a WordCount class to parse through the text and
                count the number of instances of each word.
                """;
        final var wordCount = new WordCount(string);
        Map<String, Integer> expectedWordFrequency = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("create", 2),
                new AbstractMap.SimpleEntry<>("a", 2),
                new AbstractMap.SimpleEntry<>("string", 1),
                new AbstractMap.SimpleEntry<>("literal", 1),
                new AbstractMap.SimpleEntry<>("using", 1),
                new AbstractMap.SimpleEntry<>("the", 3),
                new AbstractMap.SimpleEntry<>("first", 1),
                new AbstractMap.SimpleEntry<>("two", 1),
                new AbstractMap.SimpleEntry<>("sentences", 1),
                new AbstractMap.SimpleEntry<>("of", 3),
                new AbstractMap.SimpleEntry<>("this", 1),
                new AbstractMap.SimpleEntry<>("exercise", 1),
                new AbstractMap.SimpleEntry<>("you", 1),
                new AbstractMap.SimpleEntry<>("will", 1),
                new AbstractMap.SimpleEntry<>("wordcount", 1),
                new AbstractMap.SimpleEntry<>("class", 1),
                new AbstractMap.SimpleEntry<>("to", 1),
                new AbstractMap.SimpleEntry<>("parse", 1),
                new AbstractMap.SimpleEntry<>("through", 1),
                new AbstractMap.SimpleEntry<>("text", 1),
                new AbstractMap.SimpleEntry<>("and", 1),
                new AbstractMap.SimpleEntry<>("count", 1),
                new AbstractMap.SimpleEntry<>("number", 1),
                new AbstractMap.SimpleEntry<>("instances", 1),
                new AbstractMap.SimpleEntry<>("each", 1),
                new AbstractMap.SimpleEntry<>("word", 1)
        );
        assertEquals(expectedWordFrequency, wordCount.getWordFrequencyMap());
    }
}