package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./target/duplicatesFinder.jar"), duplicatesVisitor);
        duplicatesVisitor.getDuplicates().forEach((key, value) -> {
            System.out.println("Files: " + value.toAbsolutePath());
        });
    }
}