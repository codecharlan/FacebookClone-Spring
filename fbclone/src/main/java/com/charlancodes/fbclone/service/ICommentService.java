package com.charlancodes.fbclone.service;

import com.charlancodes.fbclone.model.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    Optional<Comment> deleteComment(Long commentId);

    List<Comment> getAllComment();

    Optional<Comment> getCommentsByPostId(Long postId);
}
