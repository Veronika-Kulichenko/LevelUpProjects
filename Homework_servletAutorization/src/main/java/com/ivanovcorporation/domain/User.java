package com.ivanovcorporation.domain;

import javax.management.relation.Role;

/**
 * Created by Сергей on 13.03.2016.
 */
public class User {

    public User(Long userid, String login, String password, String name, String role, Long customerid) {
        this.userid = userid;
        this.login = login;
        this.password = password;
        this.name = name;
        this.role = role;
        this.customerid = customerid;
    }

    private Long userid;

    private String login;

    private String password;

    private String name;

    private String role;

    private Long customerid;


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }
}
