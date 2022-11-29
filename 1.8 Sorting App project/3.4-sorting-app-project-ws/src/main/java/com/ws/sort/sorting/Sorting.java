package com.ws.sort.sorting;

import com.ws.sort.validator.Validator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class Sorting {

    final static Logger logger = Logger.getLogger(Sorting.class.toString());
    private final Validator validator;

    public Sorting() {
        this.validator = new Validator();
    }

    public Sorting(Validator validator) {
        this.validator = validator;
    }

    public void sort(int[] array) {

        logger.info("Start sort");
        if (validator.validateStringForNextSort(array)) {
            Arrays.sort(array);
            logger.info("Sorted successfull");
        } else {
            logger.log(Level.ERROR, "Validaton of array failed");
            throw new IllegalArgumentException("Validaton of array failed");
        }
    }
}
