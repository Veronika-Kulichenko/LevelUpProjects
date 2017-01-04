package com.company.levelUP.homeWork.homeWork14;

import java.util.Comparator;


public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getAge() > o2.getAge()){
            return 1;
        }
        return -1;
    }
}
