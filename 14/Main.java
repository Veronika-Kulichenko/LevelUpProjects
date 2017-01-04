package com.company.levelUP.homeWork.homeWork14;


import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {


        TreeMap<Student, Student> map = new TreeMap<>(new StudentComparatorLastNames());
        Student s1 = new Student("Nika", "Kulichenko", 23, 1);
        Student s2 = new Student("Natalia", "Kulichenko", 46, 2);
        Student s3 = new Student("Oleg", "Kulichenko", 46, 3);
        Student s4 = new Student("Alexandr", "Shegeda", 25, 4);
        Student s5 = new Student("Andrey", "Shegeda", 23, 5);
        Student s6 = new Student("Natalia", "Shegeda", 50, 6);
        Student s7 = new Student("Aleksey", "Shegeda", 33, 7);

        map.put(s4, s4);
        map.put(s2, s2);
        map.put(s6, s6);
        map.put(s5, s5);
        map.put(s1, s1);
        map.put(s3, s3);
        map.put(s7, s7);

        for (Map.Entry<Student, Student> entry : map.entrySet()){
            System.out.println(entry.getKey());
        }
    }

}
