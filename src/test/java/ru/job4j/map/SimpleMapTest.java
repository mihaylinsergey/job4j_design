package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class SimpleMapTest {

    SimpleMap<Integer, Integer> table;

    @Before
    public void initData() {
        table = new SimpleMap<>();
        table.put(1, 1);
        table.put(2, 2);
        table.put(3, 3);
    }

    @Test
    public void testPut() {
        Assert.assertTrue(table.put(4, 4));
        Assert.assertFalse(table.put(1, 4));
    }

    @Test
    public void testGet() {
        Assert.assertEquals(Integer.valueOf(1), table.get(1));
        Assert.assertEquals(null, table.get(4));
    }

    @Test
    public void testRemove() {
        Assert.assertTrue(table.remove(1));
        Assert.assertFalse(table.remove(4));
    }

    @Test
    public void testIterator() {
        Iterator<Integer> iterator = table.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        table = new SimpleMap<>();
        table.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = table.iterator();
        table.put(6, 6);
        iterator.next();
    }
}