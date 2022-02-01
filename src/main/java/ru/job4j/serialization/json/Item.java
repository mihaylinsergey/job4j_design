package ru.job4j.serialization.json;

import java.util.Arrays;

public class Item {
    private boolean available;
    private int iD;
    private Title title;
    private String[] accessories;

    public Item(boolean available, int iD, Title title, String[] accessories) {
        this.available = available;
        this.iD = iD;
        this.title = title;
        this.accessories = accessories;
    }

    @Override
    public String toString() {
        return "Item{"
              + "available=" + available
              +  ", iD=" + iD
              +  ", title=" + title
              +  ", accessories=" + Arrays.toString(accessories)
              +  '}';
    }
}
