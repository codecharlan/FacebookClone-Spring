package com.charlancodes.fbclone.repository;

import com.charlancodes.fbclone.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeRepo extends JpaRepository<Like, Long> {
}
