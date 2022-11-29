package com.ws.sort.inputArrayForNextSort;

import java.util.Scanner;

public class InputArrayForNextSort {

    public String inputArrayFromConsole() {
        
        String input;
        String messageStartInput = "Input up to ten arguments as integer values, separated by space:";
        System.out.println(messageStartInput);
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }
}
