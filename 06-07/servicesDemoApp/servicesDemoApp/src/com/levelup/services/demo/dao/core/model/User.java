package com.levelup.services.demo.dao.core.model;

public class User {

    private long id;
    private String email;
    private Roles userRole;
    private String password;
    private Language language;
    private long userDetailsId;

    public User(long id, String email, Roles userRole, String password, Language language, long userDetailsId) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
        this.password = password;
        this.language = language;
        this.userDetailsId = userDetailsId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Roles getUserRole() {
        return userRole;
    }

    public void setUserRole(Roles userRole) {
        this.userRole = userRole;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(long userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", password='" + password + '\'' +
                ", language=" + language +
                ", userDetailsId=" + userDetailsId +
                '}';
    }
}
