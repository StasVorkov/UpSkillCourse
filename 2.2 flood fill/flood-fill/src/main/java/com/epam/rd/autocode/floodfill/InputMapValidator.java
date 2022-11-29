package com.epam.rd.autocode.floodfill;

public class InputMapValidator {

    public static boolean validateInputMap(String map, FloodLogger logger) {

        if (map == null || map.isEmpty()) {
            System.out.print("Inputted map is empty");
            logger.log("User has inputted empty map");
            return false;
        } else return true;
    }
}
