package com.levelup.services.demo.dao.core.model.advertisement;

import com.levelup.services.demo.dao.core.model.Section;
import com.levelup.services.demo.dao.core.model.User;

public class BeautyAdvertisement extends Advertisement {

    public BeautyAdvertisement(String description, String[] photos, User user) {
        super(Section.Beauty, description, photos, user);
    }
}
