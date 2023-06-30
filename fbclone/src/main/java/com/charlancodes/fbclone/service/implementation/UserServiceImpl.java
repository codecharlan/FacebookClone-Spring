package com.charlancodes.fbclone.service.implementation;

import com.charlancodes.fbclone.model.User;
import com.charlancodes.fbclone.repository.IUserRepo;
import com.charlancodes.fbclone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepo userRepo;

    @Autowired
    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepo.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmailAddress(email);
    }

    @Override
    public User updateUser(User user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        userRepo.deleteById(user.getId());
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
