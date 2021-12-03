package ru.job4j.set;


import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);


    @Override
    public boolean add(T value) {
       boolean rsl = !contains(value);
       if (rsl) {
           set.add(value);
       }
       return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T i : set) {
            if (Objects.equals(i, value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return Objects.equals(set, simpleSet.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }

     public static void main(String[] args) {
        Set<Integer> set = new SimpleSet<>();
        set.add(null);
        System.out.println(set);
        System.out.println(set.contains(null));
    }
}