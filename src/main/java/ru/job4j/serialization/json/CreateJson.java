package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreateJson {
    public static void main(String[] args) {
        Item example = new Item(true, 123456, new Title("smartphone"),
                new String[] {"headphone", "case"});

        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(example));

        /* Модифицируем json-строку */
        final String exampleOut =
                "{"
                        + "\"available\":true,"
                        + "\"iD\":123456,"
                        + "\"title\":"
                        + "{"
                        + "\"title\":\"smartphone\""
                        + "},"
                        + "\"accessories\":"
                        + "[\"headphone\",\"case\"]"
                        + "}";
        final Item outJson = gson.fromJson(exampleOut, Item.class);
        System.out.println(outJson);
    }
}