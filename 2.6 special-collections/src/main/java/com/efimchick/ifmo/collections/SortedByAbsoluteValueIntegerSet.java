package com.efimchick.ifmo.collections;

import java.util.*;

class SortedByAbsoluteValueIntegerSet implements Set {

    private final TreeSet<Integer> delegateSet = new TreeSet<>(Comparator.comparingInt(Math::abs));

    @Override
    public int size() {
        return delegateSet.size();
    }

    @Override
    public boolean isEmpty() {
        return delegateSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegateSet.contains(o);
    }

    @Override
    public Iterator iterator() {
        return delegateSet.iterator();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Object o) {
        return delegateSet.add((Integer) o);
    }

    @Override
    public boolean remove(Object o) {
        return delegateSet.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return delegateSet.addAll(c);
    }

    @Override
    public void clear() {
        delegateSet.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "set=" + delegateSet;
    }
}
