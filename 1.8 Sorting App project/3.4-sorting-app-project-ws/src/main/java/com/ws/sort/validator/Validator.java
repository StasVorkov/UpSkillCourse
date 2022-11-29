package com.ws.sort.validator;

public class Validator {

    public boolean validateStringForNextSort(int[] arrayOfInt) {

        return arrayOfInt != null && arrayOfInt.length != 0 && arrayOfInt.length <= 10;
    }
}
