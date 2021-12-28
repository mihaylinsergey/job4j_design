package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("String[] args is empty");
        }
        if (validateArgs(args)) {
            for (var i : args) {
                long count = i.chars().filter(x -> x == '=').count();
                if (count == 1.0) {
                    values.put(i.substring(1, i.indexOf("=")),
                                i.substring(i.indexOf("=") + 1));
                }
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static boolean validateArgs(String[] args) {
        boolean rsl = false;
        for (var i : args) {
            if (i.startsWith("-") && i.contains("=")) {
                String key = i.substring(1, i.indexOf("="));
                String value = i.substring(i.indexOf("=") + 1);
                if (!key.isEmpty() && !value.isEmpty()) {
                    rsl = true;
                } else {
                    throw new IllegalArgumentException("Key "
                            + key + " or Value " + value + " is empty");
                }
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