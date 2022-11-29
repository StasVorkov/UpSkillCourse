package com.epam.rd.autotasks;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsCounterHP {

    private static final String REG_EX_FOR_FIND_ALL_WORDS = "(\\p{L}{4,100})|(\\d{4})";
    private static final int MIN_OCCURANCE = 10;

    private final Map<String, Integer> mapWords = new TreeMap<>();
    private final TreeSet<Map.Entry<String, Integer>> treeSet = new TreeSet<>(new ComparatorEntryByValue());
    private final PrintAble<Map.Entry<String, Integer>> printer;

    public WordsCounterHP() {
        this.printer = new StatisticPrinterEntrySet();
    }

    public String countWords(List<String> lines) {

        countRepeatedWords(lines);
        createSetWithRepeatWordsMoreThan10(mapWords);
        return printer.printStatistic(treeSet);
    }

    private void countRepeatedWords(List<String> lines) {

        Pattern word = Pattern.compile(REG_EX_FOR_FIND_ALL_WORDS);
        Matcher matcher = word.matcher(lines.toString());
        while (matcher.find()) {
            if (!mapWords.containsKey(matcher.group().toLowerCase())) {
                mapWords.putIfAbsent(matcher.group().toLowerCase(), 1);
            } else {
                mapWords.put(matcher.group().toLowerCase(), mapWords.get(matcher.group().toLowerCase())+1);
            }
        }
    }

    private void createSetWithRepeatWordsMoreThan10(Map<String, Integer> mapWords) {
        for (Map.Entry<String, Integer> entry : mapWords.entrySet()) {
            if (entry.getValue() >= MIN_OCCURANCE) {
                treeSet.add(entry);
            }
        }
    }
}

