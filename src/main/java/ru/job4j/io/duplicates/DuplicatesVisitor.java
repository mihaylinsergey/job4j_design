package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> list = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty count = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!list.containsKey(count)) {
            list.put(count, new ArrayList<>());
            list.get(count).add(file);
        } else {
            List<Path> item = list.get(count);
            item.add(file);
            list.replace(count, item);
        }
        return FileVisitResult.CONTINUE;
    }

    public Map<FileProperty, List<Path>> getDuplicates() {
        return list;
    }
}