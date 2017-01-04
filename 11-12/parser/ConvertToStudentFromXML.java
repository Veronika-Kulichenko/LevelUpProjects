package com.levelup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by java on 18.06.2016.
 */
public class ConvertToStudentFromXML {

    public static String getStudentFromFile(File file) {
        String result = "";

        try {
            FileReader reader = new FileReader(file);

            int data = 0;
            while ((data = reader.read()) >= 0) {
                result += (char) data;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Student convert(String studentLine) {
        Student student = null;

        String[] arr = studentLine.replaceAll("\t", "").split("\r\n");

        for (String tag : arr) {
            if( tag.contains("<student>")) {
                student = new Student();
            } else if (tag.contains("<age>")) {
                tag = tag.replace("<age>", "").replace("</age>", "");
                student.setAge(Integer.parseInt(tag));
            }
        }

        return student;
    }
}
