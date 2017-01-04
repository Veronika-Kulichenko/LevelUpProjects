package com.levelup;

import com.levelup.io.Example1;
import com.levelup.io.Example2;
import com.levelup.io.Example3;
import com.levelup.io.Example4;

import java.io.File;

public class Main {

    public static void main(String[] args) {
//        String textFromKeyboard = Example1.readFromKeyboard();

//        System.out.println("Result: " + textFromKeyboard);


//        Example2.writeToConsole(textFromKeyboard);

//        File file = new File("src\\com\\levelup\\io\\input.txt");
//
//        String s2 = Example3.readFromFile(file);
//        System.out.println(s2);

//        File output = new File("src\\com\\levelup\\io\\output.txt");
//        Example4.writeToFile(textFromKeyboard, output);

//        Student student = new Student("Taras", "Gorb", 22, 1);
//
//        System.out.println(student);

        String studentLine = ConvertToStudentFromXML.getStudentFromFile(new File("src\\com\\levelup\\io\\studentList.xml"));
        ConvertToStudentFromXML.convert(studentLine);
    }
}
