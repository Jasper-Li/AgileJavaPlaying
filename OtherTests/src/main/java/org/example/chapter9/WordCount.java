package org.example.chapter9;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCount {
    private final String data;

    private Map<String, Integer> wordFrequencyMap = new HashMap<>();
    WordCount(String data){
        this.data = data;
        final var words = split(data);
        for(final var word : words) {
            final var key = word.toLowerCase();
            final var value = wordFrequencyMap.getOrDefault(key, 0);
            wordFrequencyMap.put(key, value+1);
        }
    }
    public Map<String, Integer> getWordFrequencyMap() {
        return wordFrequencyMap;
    }
    static List<String> split(String data) {
        var stage1 = data.split("\\W+");
        return Arrays.asList(stage1);
    }

}
