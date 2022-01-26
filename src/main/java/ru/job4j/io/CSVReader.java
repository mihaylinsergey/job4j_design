package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        if (validate(argsName)) {
            List<StringBuilder> result = new ArrayList<>();
            try (FileReader reader = new FileReader(argsName.get("path"));
                 var scanner = new Scanner(reader)) {
                int[] columnNumber = prepareFilter(argsName);
                while (scanner.hasNextLine()) {
                    String[] tempLine = scanner.nextLine().split(argsName.get("delimiter"));
                    StringBuilder needLine = new StringBuilder();
                    needLine.append(tempLine[0]);
                    for (int i = 1; i < columnNumber.length; i++) {
                        needLine.append(";" + tempLine[i]);
                    }
                   result.add(needLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if ("stdout".equals(argsName.get("out"))) {
                result.forEach(System.out::println);
            } else {
                try (PrintWriter out = new PrintWriter(
                             new FileWriter(argsName.get("out")))) {
                     for (var i : result) {
                         out.println(i);
                     }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static boolean validate(ArgsName argsName) {
        boolean rsl = false;
        String[] values = {"path", "delimiter", "out", "filter"};
        Set<String> check = new HashSet<>();
        for (var value : values) {
            check.add(argsName.get(value));
        }
        if (check.size() == 4) {
            rsl = true;
        } else {
            throw new IllegalArgumentException("There are not correct arguments");
        }
        Path start = Paths.get(argsName.get("path"));
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toFile().getAbsoluteFile()));
        }
        if (!("stdout".equals(argsName.get("out")))) {
            Path file = Paths.get(argsName.get("out"));
            if (!file.toFile().exists()) {
                throw new IllegalArgumentException(String.format("Not exist %s", start.toFile().getAbsoluteFile()));
            }
        }
        return rsl;
    }

    private static int[] prepareFilter(ArgsName argsName) {
        String[] paramFilter = argsName.get("filter").split(",");
        int[] columnNumbers = new int[paramFilter.length];
        try (FileReader reader = new FileReader(argsName.get("path"));
             Scanner scan = new Scanner(reader)) {
            String[] tableTitle = scan.nextLine().split(";");
            int temp = 0;
            for (int i = 0; i < paramFilter.length; i++) {
                for (int j = 0; j < tableTitle.length; j++) {
                    if (paramFilter[i].equals(tableTitle[j])) {
                        columnNumbers[temp++] = j;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnNumbers;
    }
}