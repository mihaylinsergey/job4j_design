package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validate(String[] args) {
        ArgsName keyValue = ArgsName.of(new String[]{args[3], args[4], args[5]});
        if (keyValue.get("d").isEmpty() || keyValue.get("e").isEmpty() || keyValue.get("o").isEmpty()) {
            throw new IllegalArgumentException("Keys 'd', 'e' or 'o' are wrong");
        }
    }

    public static void main(String[] args) {
        Arrays.stream(args).forEach(System.out::println);
        validate(args);
        ArgsName keyValue = ArgsName.of(new String[]{args[3], args[4], args[5]});
        List<File> sources = new ArrayList<>();
        Path start = Paths.get(keyValue.get("d"));
        try {
            sources = Search
                    .search(start, x -> !x.toFile().getName().endsWith("e"))
                    .stream()
                    .map(x -> x.toFile())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        File target = new File(keyValue.get("o"));
        packFiles(sources, target);
    }
}