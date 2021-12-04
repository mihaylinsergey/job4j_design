package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> listIter = list.listIterator(index);
        listIter.add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> listIter = list.listIterator(index + 1);
        listIter.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> listIter = list.listIterator();
        while (listIter.hasNext()) {
            if (filter.test(listIter.next())) {
                listIter.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> listIter = list.listIterator();
        while (listIter.hasNext()) {
            if (filter.test(listIter.next())) {
                listIter.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> listIter = list.listIterator();
         while (listIter.hasNext()) {
             if (elements.contains(listIter.next())) {
                 listIter.remove();
                }
            }

        }
   }