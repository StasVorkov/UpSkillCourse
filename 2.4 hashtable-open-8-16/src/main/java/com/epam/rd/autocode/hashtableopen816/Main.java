package com.epam.rd.autocode.hashtableopen816;

public class Main {
    public static void main(String[] args) {

        HashtableOpen8to16 table = HashtableOpen8to16.getInstance();
        table.insert(1, 1);
        table.insert(1, 11);
        table.insert(3, 3);
        table.insert(4, 4);
        table.insert(2, 2);
        table.insert(3, 33);
        table.insert(9, 999);
        table.insert(17, 1818);   //17 key -> index = 1, but will write with index 6 instead of 1
        table.search(17);   // we need 6 iterations to find this entry
        System.out.println(table.search(9));
    }
}
