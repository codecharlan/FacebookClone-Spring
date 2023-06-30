package com.charlancodes.fbclone.service.implementation;

import com.charlancodes.fbclone.model.Post;
import com.charlancodes.fbclone.model.User;
import com.charlancodes.fbclone.repository.IPostRepo;
import com.charlancodes.fbclone.repository.IUserRepo;
import com.charlancodes.fbclone.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements IPostService {
    private final IPostRepo postRepo;
    private final IUserRepo userRepo;

    @Autowired
    public PostServiceImpl(IPostRepo postRepo, IUserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Optional<Post> getPostById(long id) {
        return postRepo.findById(id);
    }

    @Override
    public Post createPost(Post post) {
//        Optional<User> users = userRepo.findById(userid);
//        if (users.isPresent()) {
            postRepo.save(post);
//        }
        return post;
    }

    @Override
    public Post updatePost(Post post) {
        postRepo.save(post);
        return post;
    }

    @Override
    public Optional<Post> deletePost(long id) {
        postRepo.deleteById(id);
        return Optional.empty();
    }

    @Override
    public List<Post> getAllPost() {
        return postRepo.findAll();
    }
}
