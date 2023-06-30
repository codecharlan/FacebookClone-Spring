package com.charlancodes.fbclone.service;

import com.charlancodes.fbclone.model.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    Optional<Post> getPostById(long id);

    Post createPost(Post post);

    Post updatePost(Post post);

    Optional<Post> deletePost(long id);

    List<Post> getAllPost();
}
