package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordsOneStream {

    private static final String REG_EX_NOT_WORDS = "[\\P{L}+&&^\\D{4}]";
    private static final int MIN_AMOUNT_SYMBOLS = 4;
    private static final int MIN_OCCURANCE = 10;
    private static final String STRING_FORMAT = "%s - %d\n";

    public String countWords(List<String> lines) {

        String result = lines.stream()
                .map(s -> s.split(REG_EX_NOT_WORDS))
                .flatMap(array -> Arrays.stream(array)
                        .map(String::toLowerCase))
                .filter(s -> s.length() >= MIN_AMOUNT_SYMBOLS)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() >= MIN_OCCURANCE)
                .sorted(Map.Entry.comparingByKey())
                .sorted((entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue()))
                .map(entry -> String.format(STRING_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining());

        return deleteLastCharFromResult(result);
    }

    private String deleteLastCharFromResult(String result) {

        StringBuilder sb = new StringBuilder();
        sb.append(result);
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
