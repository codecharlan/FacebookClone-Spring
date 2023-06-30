package com.charlancodes.fbclone.repository;

import com.charlancodes.fbclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
    User findByEmailAddress(String email);
}
