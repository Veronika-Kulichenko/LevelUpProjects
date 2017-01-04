package com.company.levelUP.homeWork.homeWork14;

/**
 * Created by java on 18.06.2016.
 */
public class Student implements Comparable<Student> {

    private String firstName;
    private String lastName;
    private int age;
    private long id;

    public Student() {
    }

    public Student(int age, long id) {
        this.age = age;
        this.id = id;
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

//    @Override
//    public String toString() {
//        return "<student>\r\n" +
//                    "\t<age>" + age + "</age>\n" +
//                    "\t<firstName>" + firstName + "</firstName>\n" +
//                    "\t<lastName>" + lastName + "</lastName>\n" +
//                "</student>\n";
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id == student.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if(this.getAge() == o.getAge()){
            return 0;
        } else if(this.getAge() < o.getAge()){
            return 1;
        }
        return -1;
    }
}
