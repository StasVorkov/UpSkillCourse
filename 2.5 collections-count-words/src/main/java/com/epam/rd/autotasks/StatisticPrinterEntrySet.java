package com.epam.rd.autotasks;

import java.util.Map;
import java.util.TreeSet;

public class StatisticPrinterEntrySet implements PrintAble<Map.Entry<String, Integer>> {

    public String printStatistic(TreeSet<Map.Entry<String, Integer>> set) {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Integer> entry : set) {
            sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
