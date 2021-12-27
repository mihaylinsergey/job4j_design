package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        if (validateArgs(args)) {
            Arrays.stream(args).
                    forEach(x -> values.put(x.substring(1, x.indexOf("=")),
                    x.substring(x.indexOf("=") + 1)));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static boolean validateArgs(String[] args) {
        boolean rsl = false;
        for (var i : args) {
            String key = i.substring(1, i.indexOf("="));
            String value = i.substring(i.indexOf("=") + 1);
            if (i.startsWith("-") && !key.isEmpty()
                    && !value.isEmpty() && i.contains("=")) {
                rsl = true;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}