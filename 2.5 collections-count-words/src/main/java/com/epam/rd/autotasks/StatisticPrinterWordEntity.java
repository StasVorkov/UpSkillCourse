package com.epam.rd.autotasks;

import java.util.TreeSet;

public class StatisticPrinterWordEntity implements PrintAble<WordEntity> {

    public String printStatistic(TreeSet<WordEntity> set) {

        StringBuilder sb = new StringBuilder();

        for (WordEntity wordEntity : set) {
            if (wordEntity.getFrequency()>=10)
            sb.append(wordEntity.getText()).append(" - ").append(wordEntity.getFrequency()).append("\n");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
