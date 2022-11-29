package com.efimchick.ifmo.collections;


import java.util.*;

class PairStringList implements List<String> {

    private static final int OFFSET_BY_1_CHARACTER = 1;
    private static final int OFFSET_BY_2_CHARACTERS = 2;
    private final List<String> delegateList = new ArrayList<>();

    @Override
    public int size() {
        return delegateList.size();
    }

    @Override
    public boolean isEmpty() {
        return delegateList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return delegateList.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegateList.toArray();
    }

    @Override
    public boolean add(String s) {
        delegateList.add(s);
        return delegateList.add(s);
    }

    @Override
    public boolean remove(Object o) {
        delegateList.remove(o);
        return delegateList.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object o : c) {
            delegateList.add((String) o);
            delegateList.add((String) o);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (isOdd(index)) {
            for (Object o : c) {
                delegateList.add(index++ + OFFSET_BY_1_CHARACTER, (String) o);
                delegateList.add(index++, (String) o);
            }
        } else for (Object o : c) {
            delegateList.add(index++, (String) o);
            delegateList.add(index++, (String) o);
        }
        return true;
    }

    public boolean isOdd(int index) {
        return index % 2 != 0;
    }

    @Override
    public void clear() {
        delegateList.clear();
    }

    @Override
    public String get(int index) {
        return delegateList.get(index);
    }

    @Override
    public String set(int index, String s) {
        if (isOdd(index)) {
            delegateList.set(index, s);
            delegateList.set(index - OFFSET_BY_1_CHARACTER, s);
        } else {
            delegateList.set(index, s);
            delegateList.set(index + OFFSET_BY_1_CHARACTER, s);
        }
        return null;
    }

    @Override
    public void add(int index, String s) {
        if (isOdd(index)) {
            delegateList.add(index + OFFSET_BY_1_CHARACTER, s);
            delegateList.add(index + OFFSET_BY_2_CHARACTERS, s);
        } else {
            delegateList.add(index, s);
            delegateList.add(index + OFFSET_BY_1_CHARACTER, s);
        }
    }

    @Override
    public String remove(int index) {
        delegateList.remove(index * 2 - OFFSET_BY_1_CHARACTER);
        return delegateList.remove(index * 2 - OFFSET_BY_2_CHARACTERS);
    }

    @Override
    public int indexOf(Object o) {
        return delegateList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return delegateList.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return delegateList.toArray(a);
    }

    @Override
    public String toString() {
        return delegateList.toString();
    }
}