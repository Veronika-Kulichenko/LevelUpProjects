package com.levelup.services.demo.dao.core.model.advertisement;

import com.levelup.services.demo.dao.core.model.Section;
import com.levelup.services.demo.dao.core.model.User;

public class HealthAdvertisement extends Advertisement {

    public HealthAdvertisement(String description, String[] photos, User user) {
        super(Section.Health, description, photos, user);
    }
}
