package com.epam.rd.autotasks;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Words {

    private static final String REG_EX_NOT_WORDS = "[\\P{L}+&&^\\D{4}]";
    private static final int MIN_AMOUNT_SYMBOLS = 4;
    private static final int MIN_OCCURANCE = 10;
    private static final String STRING_FORMAT = "%s - %d\n";

    public String countWords(List<String> lines) {

        List<String> sourceList = createSourceListContainingRepeatedWords(lines);
        Map<String, Integer> mapWords = createMapWords(sourceList);
        String result = sortMapWords(mapWords);
        return deleteLastCharFromResult(result);
    }


    private List<String> createSourceListContainingRepeatedWords(List<String> lines) {

        List<String> list = lines.stream()
                .map(s -> s.split(REG_EX_NOT_WORDS))
                .flatMap(array -> Arrays.stream(array)                       //.forEach(strings -> LIST_WORDS.addAll(Arrays.asList(strings)));
                        .map(String::toLowerCase))
                .filter(s -> s.length() >= MIN_AMOUNT_SYMBOLS)
                .collect(toList());

        return list;
    }

    private Map<String, Integer> createMapWords(List<String> sourceList) {

        Map<String, Integer> mapW = new HashMap<>();
        sourceList.forEach(s -> mapW.put(s, mapW.getOrDefault(s, 0) + 1));      //.collect(Collectors.groupingBy(s -> s, Collectors.counting()))

        return mapW;
    }

    private String sortMapWords(Map<String, Integer> mapWords) {

        String result = mapWords.entrySet().stream()
                .filter(entry -> entry.getValue() >= MIN_OCCURANCE)
                .sorted(Map.Entry.comparingByKey())
                .sorted((entry1, entry2) -> (entry2.getValue() - entry1.getValue()))
                .map(entry -> String.format(STRING_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining());

        return result;
    }

    private String deleteLastCharFromResult(String result) {

        StringBuilder sb = new StringBuilder();
        sb.append(result);
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
