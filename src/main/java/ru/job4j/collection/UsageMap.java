package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> pocht = new HashMap<>();
        pocht.put("parsentev1@yandex.ru", "Petr Arsentev");
        pocht.put("parsentev2@yandex.ru", "Petr Arsentev");
        pocht.put("parsentev3@yandex.ru", "Petr Arsentev");
        pocht.put("parsentev4@yandex.ru", "Petr Arsentev");
        pocht.put("vpetrov5@yandex.ru", "Vano Petrov");
        for (String key : pocht.keySet()) {
            System.out.println(key + " ФИО: " + pocht.get(key));
        }
    }
}
