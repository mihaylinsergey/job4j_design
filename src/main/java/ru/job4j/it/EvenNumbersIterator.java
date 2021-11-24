package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (index < data.length) {
            while (!(data[index] % 2 == 0)) {
                index++;
                break;
            }
        }
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    @Override
    public String toString() {
        return "EvenNumbersIterator{"
               + "data=" + Arrays.toString(data)
              + ", index=" + index
              +  '}';
    }
}
