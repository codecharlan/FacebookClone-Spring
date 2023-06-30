//package com.charlancodes.fbclone.controller;
//
//import com.charlancodes.fbclone.model.Comment;
//import com.charlancodes.fbclone.service.ICommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/api/comment")
//public class CommentController {
//    private final ICommentService commentService;
//
//    @Autowired
//    public CommentController(ICommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @GetMapping
//    public String getAllComment(Model model) {
//        List<Comment> comment = commentService.getAllComment();
//        model.addAttribute("comments", comment);
//        return "comment-list";
//    }
//
//    @PostMapping
//    public String createComment(@ModelAttribute("comment") Comment comment) {
//        Comment createdComment = commentService.createComment(comment);
//        return "redirect:/api/comment";
//    }
//
//    @PutMapping
//    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
//        Comment updatedComment = commentService.updateComment(comment);
//        return ResponseEntity.ok(updatedComment);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
//        Optional<Comment> comment = commentService.deleteComment(id);
//        if (comment.isPresent()) {
//            commentService.deleteComment(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//}
// CommentController.java

package com.charlancodes.fbclone.controller;

import com.charlancodes.fbclone.model.Comment;
import com.charlancodes.fbclone.model.Post;
import com.charlancodes.fbclone.service.ICommentService;
import com.charlancodes.fbclone.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/comment")
public class CommentController {
    private final ICommentService commentService;
    private final IPostService postService;

    @Autowired
    public CommentController(ICommentService commentService, IPostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping
    public String getAllComment(Model model) {
        List<Comment> comments = commentService.getAllComment();
        model.addAttribute("comments", comments);
        return "comment-list";
    }
//    @GetMapping("/{postId}/comments")
//    public Optional<Comment> getComments(@PathVariable Long postId) {
//        return commentService.getCommentsByPostId(postId);
//    }


//    @PostMapping("/add-comment")
//    public String createComment(@ModelAttribute("comment") Comment comment) {
//        comment.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
//        Comment createdComment = commentService.createComment(comment);
//        return "dashboard";
//    }

    @GetMapping("/{postId}/comments")
    public String getCommentsForPost(@PathVariable Long postId, Model model) {
        Optional<Post> post = postService.getPostById(postId);
        if (post.isPresent()) {
            List<Comment> comments = post.get().getComments();
            model.addAttribute("comments", comments);
        }
        return "comments-list";
    }

    @PostMapping("/create-comment/postId")
    public String createComment(@PathVariable Long postId, @RequestParam String commentContent) {
        Post post = postService.getPostById(postId).orElse(null);
        if (post != null) {
            Comment comment = new Comment();
            comment.setContent(commentContent);
            post.getComments().add(comment);
            postService.updatePost(post);
        }
        return "dashboard";
    }


    @PutMapping("/{update-comment}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(comment);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
        Optional<Comment> comment = commentService.deleteComment(id);
        if (comment.isPresent()) {
            commentService.deleteComment(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
