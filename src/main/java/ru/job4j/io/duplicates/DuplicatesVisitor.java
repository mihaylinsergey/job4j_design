package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Path> list = new HashMap<>();
    private Map<FileProperty, Path> duplicates = new HashMap<>();

     @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty count = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (list.containsKey(count)) {
            duplicates.put(count, file);
        } else {
            list.put(count, file);
        }
        return FileVisitResult.CONTINUE;
    }

    public Map<FileProperty, Path> getDuplicates() {
        return duplicates;
    }
}