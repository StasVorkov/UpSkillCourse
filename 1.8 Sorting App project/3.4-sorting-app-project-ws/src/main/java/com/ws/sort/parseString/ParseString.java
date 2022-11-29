package com.ws.sort.parseString;

import com.ws.sort.sorting.Sorting;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class ParseString {

    final static Logger logger = Logger.getLogger(Sorting.class.toString());
    public int[] parseInputString(String input) {

        int[] numbers = new int[]{};
        logger.info("Start parse");

        try {
            numbers = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            logger.info("Parsed successfull");
        } catch (Exception e) {
            logger.log(Level.ERROR, "Incorrect input");
            e.printStackTrace();
        }
        return numbers;
    }
}
