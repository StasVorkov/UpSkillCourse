package com.epam.rd.autocode.decorator;

import java.util.*;

public class EvenListDecorator extends AbstractDecorator {

    List<String> sourceList;

    public EvenListDecorator(List<String> sourceList) {
        this.sourceList = sourceList;
    }

    @Override
    public int size() {
        if (sourceList.isEmpty()) {
            return 0;
        } else if (sourceList.size() % 2 == 0) {
            return sourceList.size() / 2;
        } else return sourceList.size() / 2 + 1;

    }

    @Override
    public boolean isEmpty() {
        return sourceList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return sourceList.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return sourceList.iterator();
    }

    @Override
    public Object[] toArray() {
        return sourceList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return sourceList.toArray(a);
    }

    @Override
    public boolean add(String s) {
        return sourceList.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return sourceList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return sourceList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return sourceList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return sourceList.addAll(index,c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return sourceList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return sourceList.retainAll(c);
    }

    @Override
    public void clear() {sourceList.clear();}

    @Override
    public String get(int index) {
        return sourceList.get(index * 2);
    }

    @Override
    public String set(int index, String element) {
        return sourceList.set(index,element);
    }

    @Override
    public void add(int index, String element) {sourceList.add(index,element);
    }

    @Override
    public String remove(int index) {
        return sourceList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return sourceList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return sourceList.lastIndexOf(o);
    }

    @Override
    public ListIterator<String> listIterator() {
        return getEvenList().listIterator();
    }

    private List<String> getEvenList() {
        List<String> evenIndex = new ArrayList<>();
        for (int i = 0; i < size(); i++) evenIndex.add(get(i));
        return evenIndex;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return sourceList.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return sourceList.subList(fromIndex,toIndex);
    }
}
