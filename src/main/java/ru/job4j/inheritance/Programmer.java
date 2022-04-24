package ru.job4j.inheritance;

public class Programmer extends Engineer {
    String code;

    public Programmer(String name, String surname, String education, String birthday, String code) {
        super(name, surname, education, birthday);
        this.code = code;
    }

    public String writCode() {
        return "Write code";
    }
}
