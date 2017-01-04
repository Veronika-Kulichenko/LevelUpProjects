package com.levelup.core.entity;

import javax.persistence.*;

/**
 * Created by Alexandr Shegeda on 30.09.16.
 */
@Entity(name = "user_details")
@Table(name = "USER_DETAILS")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column
    @Enumerated(value = EnumType.ORDINAL)
    private Sex sex;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userDetails", cascade = CascadeType.ALL)
    private Address address;

    @Column
    private String phoneNumber;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public UserDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
