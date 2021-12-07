package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        modCount++;
        int h = key.hashCode();
        int hash = hash(h);
        int i = indexFor(hash);
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[i] == null) {
            table[i] = new MapEntry<K, V>(key, value);
            rsl = true;
            count++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] currentTable = table;
        table = new MapEntry[capacity];
        for (var x : currentTable) {
            if (x != null) {
                put(x.key, x.value);
            }
        }
    }

    @Override
    public V get(K key) {
        modCount++;
        V rsl = null;
        int h = key.hashCode();
        int hash = hash(h);
        int i = indexFor(hash);
        if (table[i] != null) {
            rsl = table[i].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        modCount++;
        boolean rsl = false;
        int h = key.hashCode();
        int hash = hash(h);
        int i = indexFor(hash);
        if (table[i] != null) {
            table[i] = null;
            rsl = true;
            count--;
        }
        return rsl;
    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            final private int expectedModCount = modCount;

            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean rsl = false;
                for (int i = point; i < table.length; i++) {
                    if (table[i] != null) {
                        point = i;
                        rsl = true;
                        break;
                    }
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key)
                    && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

}