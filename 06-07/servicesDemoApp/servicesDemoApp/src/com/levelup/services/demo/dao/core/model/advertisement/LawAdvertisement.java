package com.levelup.services.demo.dao.core.model.advertisement;

import com.levelup.services.demo.dao.core.model.Section;
import com.levelup.services.demo.dao.core.model.User;

public class LawAdvertisement extends Advertisement{

    public LawAdvertisement(String description, String[] photos, User user) {
        super(Section.Law, description, photos, user);
    }
}
