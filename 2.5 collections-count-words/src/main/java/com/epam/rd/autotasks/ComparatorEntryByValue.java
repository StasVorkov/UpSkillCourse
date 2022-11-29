package com.epam.rd.autotasks;

import java.util.Comparator;
import java.util.Map;

public class ComparatorEntryByValue implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        int res = o2.getValue().compareTo(o1.getValue());
        return res != 0 ? res : 1;
    }
}
