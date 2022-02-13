package ru.job4j.ioexam;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchExam {

    private ArgsName argsName;
    private List<Path> files = Collections.EMPTY_LIST;

    private void handle() throws IOException {
        String searchType = argsName.get("t");
        if ("mask".equals(searchType)) {
            Pattern pattern = Pattern.compile(argsName.get("n")
                    .replace(".", "\\.")
                    .replace("*", "(\\w*)")
                    .replace("?", "(\\w)"));
            files = search(Paths.get(argsName.get("d")), x -> pattern
                    .matcher(x.toFile().getName())
                    .find());
        } else if ("name".equals(searchType)) {
            files = search(Paths.get(argsName.get("d")), x -> x.toFile()
                    .getName().equals(argsName.get("n")));
        } else if ("regex".equals(searchType)) {
            Pattern pattern = Pattern.compile(argsName.get("n"));
            files = search(Paths.get(argsName.get("d")), x -> pattern
                    .matcher(x.toFile().getName())
                    .find());
        }
    }

    private void validateArgs(String[] args) {
        argsName = ArgsName.of(args);
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("The number of arguments is not equal to 4");
        }
        Path start = Paths.get(argsName.get("d"));
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException("The argument \"d\" is not valid");
        }
    }

    private void writeOut() {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(argsName.get("o")))) {
            if (files.size() == 0) {
                System.out.println("Files not found!");
                return;
            }
            files.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Input some arguments for example: "
                + "java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt");
        SearchExam example = new SearchExam();
        example.validateArgs(args);
        example.handle();
        example.writeOut();
    }
}
