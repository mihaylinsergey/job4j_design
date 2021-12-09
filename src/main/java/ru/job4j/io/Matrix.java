package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static int[][] multiple(int size) {
        int[][] data = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                data[row][cell] = (row + 1) * (cell + 1);
            }
        }
        return data;
    }

    public static void main(String[] args) {
        int[][] example = multiple(10);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < example.length; i++) {
                for (int j = 0; j < example[i].length; j++) {
                    out.write(String.valueOf(example[i][j]).getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}