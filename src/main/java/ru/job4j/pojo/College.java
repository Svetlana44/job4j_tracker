package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Alex Riter");
        student.setGroup("3A21");
        student.setDateIn(new Date());
        System.out.println(student.getFullName() + ", group - " + student.getGroup() + " : " + student.getDateIn());
    }
}
