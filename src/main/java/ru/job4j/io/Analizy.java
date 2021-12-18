package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(
                new FileReader(source));
                PrintWriter out = new PrintWriter(
                        new FileOutputStream(target))) {
            int flag = 0;
            for (String status = in.readLine(); status != null; status = in.readLine()) {
                if (flag == 0 && status.contains("400")
                        || status.contains("500")) {
                    out.print(status.split(" ")[1] + ";");
                    flag = 1;
                } else if (flag == 1 && status.contains("400")
                            || status.contains("500")) {
                        out.print(status.split(" ")[1] + ";");
                } else if (flag != 0 && !status.contains("400")
                        && !status.contains("500")) {
                    out.print(status.split(" ")[1] + ";");
                    flag = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    Analizy example = new Analizy();
    example.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}
