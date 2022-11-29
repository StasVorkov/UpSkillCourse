package com.epam.rd.autotasks;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsCounter {

    private static final String REG_EX_FOR_FIND_ALL_WORDS = "(\\p{L}{4,100})|(\\d{4})";
    private final TreeSet<WordEntity> treeSet = new TreeSet<>();
    private final List<String> sourceList = new ArrayList<>();
    private final PrintAble<WordEntity> printer;

    public WordsCounter() {
        this.printer = new StatisticPrinterWordEntity();
    }

    public String countWords(List<String> lines) {

        createSourceListLowerCase(lines);
        countRepeatsWords();
        return printer.printStatistic(treeSet);
    }

    private void createSourceListLowerCase(List<String> lines) {

        Pattern word = Pattern.compile(REG_EX_FOR_FIND_ALL_WORDS);
        Matcher matcher = word.matcher(lines.toString());
        while (matcher.find()) {
            sourceList.add(matcher.group().toLowerCase());
        }
        Collections.sort(sourceList);
    }

    private void countRepeatsWords() {
        String compareWord = sourceList.get(0);
        int counter = 0;

        for (int i = 0; i < sourceList.size(); i++) {
            String word = sourceList.get(i);
            if (compareWord.equals(word)) {
                counter++;
            } else {
                treeSet.add(new WordEntity(compareWord, counter));
                compareWord = word;
                counter = 1;
            }
            if (i == sourceList.size() - 1) {
                treeSet.add(new WordEntity(word, counter));
            }
        }
    }
}
