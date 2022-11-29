package com.efimchick.ifmo.collections;


public class Main {
    public static void main(String[] args) {
        MedianQueue medianQueue = new MedianQueue();
        medianQueue.offer(1);
        medianQueue.offer(0);
        System.out.println(medianQueue);
        System.out.println(medianQueue.size());
    }
}
