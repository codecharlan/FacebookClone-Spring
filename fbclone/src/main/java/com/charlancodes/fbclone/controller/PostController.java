//package com.charlancodes.fbclone.controller;
//
//import com.charlancodes.fbclone.model.Post;
//import com.charlancodes.fbclone.service.IPostService;
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
//@RequestMapping("/api/post")
//public class PostController {
//
//    private final IPostService postService;
//
//    @Autowired
//    public PostController(IPostService postService) {
//        this.postService = postService;
//    }
//
//    @GetMapping
//    public String getAllPost(Model model) {
//        List<Post> posts = postService.getAllPost();
//        model.addAttribute("posts", posts);
//        return "post-list";
//    }
//
//    @PostMapping
//    public String createPost(@ModelAttribute("post") Post post) {
//        Post createdPost = postService.createPost(post);
//        return "redirect:/api/post";
//    }
//
//    @PutMapping
//    public ResponseEntity<Post> updateUser(@RequestBody Post post) {
//        Post updatedPost = postService.updatePost(post);
//        return ResponseEntity.ok(updatedPost);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePost(@PathVariable long id) {
//        Optional<Post> post = postService.deletePost(id);
//        if (post.isPresent()) {
//            postService.deletePost(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
// PostController.java

package com.charlancodes.fbclone.controller;

import com.charlancodes.fbclone.model.Post;
import com.charlancodes.fbclone.model.User;
import com.charlancodes.fbclone.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/post")
public class PostController {

    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getAllPost(Model model) {
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "post-list";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        post.ifPresent(value -> model.addAttribute("post", value));
        return "post-details";
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute("post") Post post, HttpSession session) {
        User user = (User) session.getAttribute("user");
        post.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        post.setUser(user);
        System.out.println(user);
        postService.createPost(post);
        System.out.println("post entered");
        return "dashboard";
    }

    @PutMapping("/update-post")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        Post updatedPost = postService.updatePost(post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable long id) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            postService.deletePost(post.get().getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
