package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book fantastic = new Book("StarTrack", 1000);
        Book roman = new Book("Wor and peas", 3000);
        Book coding = new Book("Clean code", 350);
        Book tragic = new Book("Romeo and Julete", 200);
        Book[] books = {fantastic, roman, coding, tragic};
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + ", pages count: " + books[i].getCountPage());
        }
        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;
        for (Book book : books) {
            System.out.println(book.getName() + ", pages count: " + book.getCountPage());
        }
        for (Book book : books) {
            if ("Clean code".equals(book.getName())) {
                System.out.println(book.getName() + ", pages count: " + book.getCountPage());
            }
        }
    }
}
