package com.ivanovcorporation.domain;

import java.sql.Date;


/**
 * Created by java on 20.02.2016.
 */
public class Customer {

    public Customer(Long id, String firstname, String lastname, Date birthdate, String address, String city, String passport, String phone) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.passport = passport;
        this.phone = phone;
    }

    public Customer(String firstname, String lastname, Date birthdate, String address, String city, String passport, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.address = address;
        this.city = city;
        this.passport = passport;
        this.phone = phone;
    }

    private Long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String address;
    private String city;
    private String passport;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer N" + id +
                "\nfirstname\t'" + firstname + '\'' +
                "\nlastname\t'" + lastname + '\'' +
                "\nbirthdate\t" + birthdate +
                "\naddress\t\t'" + address + '\'' +
                "\ncity\t\t'" + city + '\'' +
                "\npassport\t'" + passport + '\'' +
                "\nphone\t\t'" + phone + '\'' +"\n";
    }
}
