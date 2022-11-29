package com.epam.rd.autocode.floodfill;

public class Main {
    public static void main(String[] args) {
        FloodLoggerImpl logger = new FloodLoggerImpl();
        String map = "█████\n" +
                     "█░░░█\n" +
                     "█████";
        FloodFill.getInstance().flood(map,logger);
    }
}
