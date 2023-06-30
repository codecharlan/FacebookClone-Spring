package com.charlancodes.fbclone.service;

import com.charlancodes.fbclone.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUserById(long id);
    User getUserByEmail(String email);
    User createUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    List<User> getAllUser();
}
