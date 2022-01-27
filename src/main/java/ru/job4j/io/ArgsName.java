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
        for (var arg : args) {
            values.put(validateWord(arg)[0], validateWord(arg)[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static String[] validateWord(String word) {
        String[] rsl = new String[2];
        long count = word.chars().filter(x -> x == '=').count();
        if (word.startsWith("-") && word.contains("=") && count == 1.0) {
            rsl[0] = word.substring(1, word.indexOf("="));
            rsl[1] = word.substring(word.indexOf("=") + 1);
            if (rsl[0].isEmpty() || rsl[1].isEmpty()) {
                throw new IllegalArgumentException("Key "
                        + rsl[0] + " or Value " + rsl[1] + " is empty");
            }
        } else {
                throw new IllegalArgumentException("The format of the arguments is not correct");
        }
        return rsl;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding==UTF-8"});
        System.out.println(zip.get("out"));
        System.out.println(zip.get("encoding"));
    }
}