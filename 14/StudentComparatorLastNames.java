package com.company.levelUP.homeWork.homeWork14;

import java.util.Comparator;

public class StudentComparatorLastNames implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getId()== o2.getId()){
            return 0;
        }
        if (o1.getLastName().equals(o2.getLastName())) {
            if (o1.getFirstName().equals(o2.getFirstName())){
                if (o1.getAge() == o2.getAge()){
                    return 0;
                }
                return 1;
            }
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
        return o1.getLastName().compareTo(o2.getLastName());
    }
}


