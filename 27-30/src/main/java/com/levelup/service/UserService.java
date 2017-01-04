package com.levelup.service;

import com.levelup.core.dto.UserDTO;
import com.levelup.core.entity.User;

public interface UserService {

    boolean registerUser(User user);

    boolean isPasswordCorrect(String login, String pass);

    User getUserById(Long id);

    User getUserByEmail(String email);
}
