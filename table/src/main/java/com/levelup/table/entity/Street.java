package com.levelup.table.entity;

/**
 * Created by Alexandr Shegeda on 24.07.16.
 */
public class Street {
    private long id;
    private String streetName;

    public Street() {
    }

    public Street(String streetName) {
        this.streetName = streetName;
    }

    public Street(long id, String streetName) {
        this.id = id;
        this.streetName = streetName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
