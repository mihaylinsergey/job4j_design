package ru.job4j.io;

import java.util.List;
import java.io.*;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = in.lines()
                    .filter(x -> x.substring(x.lastIndexOf(" ") - 3,
                            x.lastIndexOf(" "))
                            .equals("404"))
                    .map(x -> x + System.lineSeparator())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
                        for (String i : log) {
                            out.print(i);
                        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::print);
        save(log, "404.txt");
    }
}