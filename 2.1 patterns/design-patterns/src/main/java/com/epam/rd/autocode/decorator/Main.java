package com.epam.rd.autocode.decorator;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final List<String> list = Arrays.asList("1,2,3,4,5,6,7,8,9,10".split(","));
        final List<String> even = Decorators.evenIndexElementsSubList(list);
        System.out.println(even.size());
    }
}
