package com.levelup.table.entity;

/**
 * Created by Alexandr Shegeda on 24.07.16.
 */
public class Citizen {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Integer streetId;

    public Citizen() {
    }

    public Citizen(Long id, String firstName, String lastName, int age, Integer streetId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.streetId = streetId;
    }

    public Citizen(String firstName, String lastName, int age, Integer streetId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.streetId = streetId;
    }

    public Citizen(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", streetId=" + streetId +
                '}';
    }
}


