package ru.job4j.inheritance;

public class Builder extends Engineer {
    private String speed;

    public Builder(String name, String surname, String education, String birthday, String speed) {
        super(name, surname, education, birthday);
        this.speed = speed;
    }

    public String build() {
        return "Build";
    }
}
