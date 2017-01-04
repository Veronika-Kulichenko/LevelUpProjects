package com.levelup.services.demo.dao.core.model.advertisement;

import com.levelup.services.demo.dao.core.model.Section;
import com.levelup.services.demo.dao.core.model.User;

import static com.levelup.services.demo.dao.core.model.Language.*;

public abstract class Advertisement {

    private String label;
    private Section section;
    private String description;
    private String[] photos;
    private User user;

    public Advertisement(Section section, String description, String[] photos, User user) {
        this.section = section;
        this.description = description;
        this.photos = photos;
        this.user = user;
        this.label = (user.getLanguage() == RU) ? section.getRuLabel() : section.getEnLabel();
    }

    public String getLabel() {
        return label;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
