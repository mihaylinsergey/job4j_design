package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size + 1 == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        if (container[size] == null) {
            container[size] = value;
        } else {
            container[size] = value;
        }
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        modCount++;
        T rsl = container[Objects.checkIndex(index, container.length)];
        container[Objects.checkIndex(index, container.length)] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T rsl = container[Objects.checkIndex(index, container.length)];
        System.arraycopy(
                container,
                Objects.checkIndex(index, container.length) + 1,
                container,
                Objects.checkIndex(index, container.length),
                container.length - Objects.checkIndex(index, container.length) - 1
        );
      container[container.length - 1] = null;
      size--;
      return rsl;
    }

    @Override
    public T get(int index) {
        modCount++;
        return container[Objects.checkIndex(index, container.length)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int expectedModCount = modCount;

            private int point;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < container.length && container[point] == null) {
                    point++;
                }
                return point < container.length && container[point] != null;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
