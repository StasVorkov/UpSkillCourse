package com.efimchick.ifmo.collections;

import java.util.*;

class MedianQueue implements Queue<Integer> {

    private static final int OFFSET_BY_1_CHARACTER = 1;
    private final LinkedList<Integer> sourceValuesQueue = new LinkedList<>();
    private LinkedList<Integer> medianValuesQueue = new LinkedList<>();

    @Override
    public int size() {
        return medianValuesQueue.size();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Integer> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        medianValuesQueue.clear();}

    @Override
    public boolean offer(Integer integer) {
        refreshSourceQueueAfterRemoving();
        medianValuesQueue.clear();
        sourceValuesQueue.offer(integer);
        medianValuesQueue = fillMedianValuesQueue(sourceValuesQueue);
        return true;
    }

    private void refreshSourceQueueAfterRemoving() {
        if (sourceValuesQueue.size() == 0 && medianValuesQueue.size() != 0) {
            for (Integer i : medianValuesQueue) {
                sourceValuesQueue.offer(i);
            }
            medianValuesQueue.clear();
        }
    }

    private LinkedList<Integer> fillMedianValuesQueue(LinkedList<Integer> queue) {
        int med;
        while (queue.size() > 0) {
            med = findMediumElementInQueue(queue);
            medianValuesQueue.addLast(med);
            queue.remove((Integer) med);
            fillMedianValuesQueue(queue);
        }
        return medianValuesQueue;
    }

    private int findMediumElementInQueue(LinkedList<Integer> queue) {
        ArrayList<Integer> list = new ArrayList<>(queue);
        int medium;
        list.sort(Integer::compareTo);

        if (list.size() == 1) {
            medium = list.get(0);
        } else {
            if (list.size() % 2 == 0) {
                medium = list.get(list.size() / 2 - OFFSET_BY_1_CHARACTER);
            } else {
                medium = list.get(list.size() / 2);
            }
        }
        return medium;
    }

    @Override
    public Integer remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer poll() {
        return medianValuesQueue.poll();
    }

    @Override
    public Integer element() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer peek() {
        return medianValuesQueue.peek();
    }

    @Override
    public String toString() {
        return "MEDIAN_QUEUE=" + medianValuesQueue;
    }
}

