package com.charlancodes.fbclone.service;

import com.charlancodes.fbclone.model.Like;

import java.util.List;
import java.util.Optional;

public interface ILikeService {
    Like addLike(Like like);

    Optional<Like> getLikeByPostId(Long postId);

    Optional<Like> removeLike(Long likeId);

    List<Like> getLikesByUserId(Long userId);
}
