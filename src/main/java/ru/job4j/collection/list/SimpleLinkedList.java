package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    transient int size;
    transient  Node<E> first;
    transient  Node<E> last;
    private  int modCount;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    private Node<E> getNextElement(Node<E> index) {
        return index.getNext();
    }

    @Override
    public E get(int index) {
        modCount++;
        Objects.checkIndex(index, size);
        Node<E> target = first;
        for (int i = 0; i < index; i++) {
            target = getNextElement(target);
        }
        return target.getItem();
    }

      @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            final private int expectedModCount = modCount;

            private int point;

            private Node<E> result = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = result.getItem();
                result = result.getNext();
                point++;
                return rsl;
            }
        };
    }

     private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }


        public E getItem() {
            return item;
        }

         public Node<E> getNext() {
            return next;
        }
    }
}
