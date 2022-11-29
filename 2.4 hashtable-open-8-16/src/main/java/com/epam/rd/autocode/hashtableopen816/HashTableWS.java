package com.epam.rd.autocode.hashtableopen816;

import java.util.Arrays;

public class HashTableWS implements HashtableOpen8to16 {

    private static final int START_CAPACITY = 8;
    private static final int DOUBLE_CAPACITY = 16;
    private static final int MULTIPLY_COEFFICIENT = 2;
    private static final int DIVIDE_COEFFICIENT = 2;
    private static final int RATIO = 4;

    private EntryHash[] hashTable;
    private int size;
    private int counter;

    public HashTableWS() {
        hashTable = new EntryHash[START_CAPACITY];
    }

    @Override
    public void insert(int key, Object value) {

        if (isHashTableFull(key)) {
            if (isCapacityAbleToIncreaseWhenHashTableIsfull()) {
                changeCapacity(hashTable.length * MULTIPLY_COEFFICIENT);
            } else throw new IllegalStateException();
        }

        int hashCode = Integer.valueOf(key).hashCode();
        int index = findIndexToHashCode(hashCode);
        addEntry(key, value, index);
    }

    @Override
    public Object search(int key) {

        int hashCode = Integer.valueOf(key).hashCode();
        int index = findIndexToHashCode(hashCode);
        this.counter = 0;
        EntryHash entry = checkBucketForEntry(key, index);

        if (entry != null) {
            return entry.getValue();
        } else {
            return null;
        }
    }

    @Override
    public void remove(int key) {

        int hashCode = Integer.valueOf(key).hashCode();
        int index = findIndexToHashCode(hashCode);
        this.counter = 0;
        EntryHash hashTableEntity = checkBucketForEntry(key, index);

        if (hashTableEntity != null) {
            hashTableEntity.remove();
            hashTableEntity.setKey(null);
            hashTableEntity.setValue(null);
            size--;
            decreaseSize();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] keys() {
        return Arrays.stream(hashTable)
                .filter(entryHash -> entryHash != null && entryHash.notRemoved())
                .map(EntryHash::getKey)
                .mapToInt(i->i)
                .toArray();
    }

    private int findIndexToHashCode(int hashCode) {
        return Math.abs(hashCode) % hashTable.length;
    }

    private void addEntry(int key, Object value, int index) {

        if (hashTable[index] != null && hashTable[index].notRemoved()) {
            if (hashTable[index].getKey() == key) {
                hashTable[index].setValue(value);
            } else {
                addEntry(key, value, indexIncrement(index));
            }
        } else {
            hashTable[index] = new EntryHash(key, value);
            size++;
        }
    }

    private int indexIncrement(int index) {

        index++;
        if (index != hashTable.length) {
            return index;
        } else {
            return 0;
        }
    }

    private boolean isCapacityAbleToIncreaseWhenHashTableIsfull() {

        return hashTable.length != DOUBLE_CAPACITY;
    }

    private boolean isHashTableFull(int key) {
        return (size == hashTable.length) && (search(key) == null);
    }

    private void changeCapacity(int newCapacity) {

        EntryHash[] oldTable = hashTable;
        hashTable = new EntryHash[newCapacity];
        size = 0;
        Arrays.stream(oldTable)
                .filter(hashTableEntry -> hashTableEntry != null && hashTableEntry.notRemoved())
                .forEach(hashTableEntry -> insert(hashTableEntry.getKey(), hashTableEntry.getValue()));
    }

    private void decreaseSize() {

        if (size != 0 && size * RATIO <= hashTable.length) {
            changeCapacity(hashTable.length / DIVIDE_COEFFICIENT);
        }
    }


    private EntryHash checkBucketForEntry(int key, int index) {

        if (this.counter == hashTable.length) {
            return null;
        }
        this.counter++;

        if (hashTable[index] != null) {
            if (hashTable[index].getKey() == key && hashTable[index].notRemoved()) {
                return hashTable[index];
            } else {
                return checkBucketForEntry(key, indexIncrement(index));
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(hashTable);
    }
}
