package ru.job4j.oop;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println(
                "Error " + active + "\n"
                        + "Status: " + status + "\n"
                        + "Мessage: " + message + "\n");
    }

    public static void main(String[] args) {
        Error error = new Error();
        Error error1 = new Error(true, 1, "Your time is empty.");
        Error error2 = new Error(false, 2, "Your error.");
        error.printInfo();
        error1.printInfo();
        error2.printInfo();

    }
}
