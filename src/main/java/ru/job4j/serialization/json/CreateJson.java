package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateJson {
    public static void main(String[] args) {

        JSONObject jsonTitle = new JSONObject("{\"title\":\"smartphone\"}");

        List<String> list = new ArrayList<>();
        list.add("headphone");
        list.add("case");
        JSONArray jsonAccessories = new JSONArray(list);

        Item example = new Item(true, 123456, new Title("smartphone"),
                new String[] {"headphone", "case"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("available", example.isAvailable());
        jsonObject.put("iD", example.getId());
        jsonObject.put("title", jsonTitle);
        jsonObject.put("accessories", jsonAccessories);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(example));
    }
}