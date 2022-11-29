package com.epam.rd.autotasks;

public class WordEntity implements Comparable<WordEntity> {

    private final String text;
    private int frequency;

    public WordEntity(String text, int frequency) {
        this.text = text;
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text + " - " + frequency + "\n";
    }


    @Override
    public int compareTo(WordEntity o) {
        int res=0;
        if ((o.getFrequency() > (this.getFrequency()))) {
            res = 1;
        } else if ((o.getFrequency() < (this.getFrequency()))) {
            res = -1;
        }
        return res != 0 ? res : 1;
    }
}
