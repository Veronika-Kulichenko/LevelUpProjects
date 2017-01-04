package com.levelup;

/**
 * Created by java on 18.06.2016.
 */
public class Student {

    private String firstName;
    private String lastName;
    private int age;
    private long id;

    public Student() {
    }

    public Student(String firstName, String lastName, int age, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "<student>\r\n" +
                    "\t<age>" + age + "</age>\n" +
                    "\t<firstName>" + firstName + "</firstName>\n" +
                    "\t<lastName>" + lastName + "</lastName>\n" +
                "</student>\n";
    }
}
