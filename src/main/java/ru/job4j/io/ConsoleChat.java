package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        boolean stop = false;
        boolean out = false;
        while (!out) {
            String word = in.nextLine();
            log.add(word + System.lineSeparator());
            switch (word) {
                case "стоп":
                    stop = true;
                    break;
                case "продолжить":
                    stop = false;
                    break;
                case "закончить":
                    out = true;
                    break;
                default:
                    if (!stop) {
                        String answer = readPhrases()
                                .get(new Random().nextInt(3));
                        log.add(answer + System.lineSeparator());
                        System.out.println(answer);
                    }
            }
        }
        saveLog(log);
        }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            rsl = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
         try (PrintWriter out = new PrintWriter(
            new FileWriter(path, Charset.forName("UTF-8"), true))) {
             for (var i : log) {
            out.print(i);
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/ConsoleChat.txt", "./data/readPhrases.txt");
        cc.run();
    }
}