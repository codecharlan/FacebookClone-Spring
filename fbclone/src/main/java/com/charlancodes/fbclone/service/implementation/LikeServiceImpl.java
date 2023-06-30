package com.charlancodes.fbclone.service.implementation;

import com.charlancodes.fbclone.model.Like;
import com.charlancodes.fbclone.repository.ILikeRepo;
import com.charlancodes.fbclone.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements ILikeService {

    private final ILikeRepo likeRepo;

    @Autowired
    public LikeServiceImpl(ILikeRepo likeRepo) {
        this.likeRepo = likeRepo;
    }

    @Override
    public Like addLike(Like like) {
        likeRepo.save(like);
        return like;
    }

    @Override
    public Optional<Like> getLikeByPostId(Long postId) {
        return Optional.of(likeRepo.getReferenceById(postId));
    }

    @Override
    public Optional<Like> removeLike(Long likeId) {
        likeRepo.deleteById(likeId);
        return null;
    }

    @Override
    public List<Like> getLikesByUserId(Long userId) {
        return likeRepo.findAll();
    }
}
