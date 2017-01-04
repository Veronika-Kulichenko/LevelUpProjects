package com.levelup.services.demo.dao.core.model.advertisement;

import com.levelup.services.demo.dao.core.model.Section;
import com.levelup.services.demo.dao.core.model.User;

/**
 * Created by Alexandr Shegeda on 30.05.16.
 */
public class AnimalsAdvertisement extends Advertisement{

    public AnimalsAdvertisement(String description, String[] photos, User user) {
        super(Section.Animals, description, photos, user);
    }
}
