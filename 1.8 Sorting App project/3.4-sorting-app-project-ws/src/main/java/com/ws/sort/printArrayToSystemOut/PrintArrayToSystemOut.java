package com.ws.sort.printArrayToSystemOut;

import com.ws.sort.validator.Validator;

import java.util.Arrays;

public class PrintArrayToSystemOut {

    private static Validator validator;

    public PrintArrayToSystemOut() {
        validator = new Validator();
    }

    public static void printArray(int[] array) {

        if (validator.validateStringForNextSort(array)) {
            System.out.println("Your sorted array: " + Arrays.toString(array));
        } else System.out.println("Print fail -> validator failed");
    }
}
