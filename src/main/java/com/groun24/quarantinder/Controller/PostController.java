package com.groun24.quarantinder.Controller;

import com.groun24.quarantinder.Modal.Post;
import com.groun24.quarantinder.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vivian
 */
@RestController
@RequestMapping("/api/posts")

public class PostController {
    @Autowired PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    public PostController(){

    }

    @GetMapping
    public List<Post> findByUserID(@RequestParam(required = false) Integer userId) {
        return userId == null ? postService.findAll() : postService.findByUserID(userId);
    }

    @PostMapping
    public void create(@RequestBody Post post)
    {
        postService.create(post);
    }

    @PutMapping
    public void update(@RequestBody Post post) {
        postService.update(post);
    }

    @DeleteMapping("/{postId}")
    public void delete(@PathVariable Integer postId) {
        postService.deleteById(postId);
    }
}
