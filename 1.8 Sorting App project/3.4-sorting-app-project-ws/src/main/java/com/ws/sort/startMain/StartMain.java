package com.ws.sort.startMain;

import com.ws.sort.inputArrayForNextSort.InputArrayForNextSort;
import com.ws.sort.parseString.ParseString;
import com.ws.sort.printArrayToSystemOut.PrintArrayToSystemOut;
import com.ws.sort.sorting.Sorting;
import com.ws.sort.validator.Validator;


public class StartMain {
    public static void main(String[] args) {
        //input
        InputArrayForNextSort inputArrayForNextSort = new InputArrayForNextSort();
        String inputFromConsole = inputArrayForNextSort.inputArrayFromConsole();

        //parse
        ParseString parseString = new ParseString();
        int[] inputArrayOFInt = parseString.parseInputString(inputFromConsole);

        //sort1
        Sorting sorting1 = new Sorting();
        sorting1.sort(inputArrayOFInt);

        //sort2
        Sorting sorting2 = new Sorting(new Validator());
        sorting2.sort(inputArrayOFInt);

        //print
        PrintArrayToSystemOut.printArray(inputArrayOFInt);
    }
}



