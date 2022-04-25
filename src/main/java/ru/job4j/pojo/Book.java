package ru.job4j.pojo;

public class Book {
    private String name;
    private int countPage;

    public Book(String name, int countPage) {
        this.name = name;
        this.countPage = countPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }
}
