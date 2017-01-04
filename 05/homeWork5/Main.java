package com.company.levelUP.homeWork.homeWork5;


import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student();
        Student student2 = new Student();

        student1.setId(100);
        student2.setId(100);
        System.out.println(student1.equals(student2));

        Set students = new HashSet();
        students.add(student1);
        students.add(student2);
        //Печатает два объекта
        System.out.println(students);


    }
}
