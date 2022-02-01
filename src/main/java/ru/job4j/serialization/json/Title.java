package ru.job4j.serialization.json;

public class Title {
    private String title;

    public Title(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Title{"
               + "title='" + title + '\''
               + '}';
    }
}
