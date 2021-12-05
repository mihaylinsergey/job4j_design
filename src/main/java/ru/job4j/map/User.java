package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar day = Calendar.getInstance();
        User one = new User("Ivan", 1, day);
        User two = new User("Ivan", 1, day);
        Map<User, Object> users = new HashMap<>();
        users.put(one, new Object());
        users.put(two, new Object());
        users.entrySet().forEach(System.out::println);
    }
}
