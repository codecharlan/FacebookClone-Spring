package com.charlancodes.fbclone.controller;

import com.charlancodes.fbclone.model.Like;
import com.charlancodes.fbclone.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("api/dolike")
public class LikeController {
    private final ILikeService likeService;

    @Autowired
    public LikeController(ILikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/add-like")
    public String addLike(@ModelAttribute("like") Like like) {
        Like createdLike = likeService.addLike(like);
        return "dashboard";
    }

    @GetMapping("/{id}/post")
    public ResponseEntity<Like> getLikeByPostId(@PathVariable long id) {
        Optional<Like> like = likeService.getLikeByPostId(id);
        return like.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<List<Like>> getLikesByUserId(@PathVariable long id) {
        List<Like> likes = likeService.getLikesByUserId(id);
        return ResponseEntity.ok().body(likes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeLike(@PathVariable long id) {
        Optional<Like> like = likeService.removeLike(id);
        if (like.isPresent()) {
            likeService.removeLike(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
