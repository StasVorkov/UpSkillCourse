package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    private final static String string1 = "Well,well,well Well,well,well Well,well,well Well,well,well ";
    private final static String string2 = "Prince,Lucca Lucca Lucca Lucca Lucca Lucca Lucca Lucca Lucca \n " +
            "Lucca Lucca Lucca just just          1812";
    private final static String string3 = "Prince,Lucca Lucca Lucca Lucca Lucca Lucca Lucca just just          1812,  1812" +
            " 1812  1812  1812  1812  1812  1812  1812  1812  1812  1812  1812" +
            "natásha natásha natásha natásha natásha natásha natásha natásha natásha natásha natásha natásha";


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add(string1);
        list.add(string2);
        list.add(string3);
        Words words = new Words();
        System.out.println(words.countWords(list));

        Stream.of(1, 2, 3).mapToDouble(Integer::intValue).forEach(System.out::println);
    }
}
