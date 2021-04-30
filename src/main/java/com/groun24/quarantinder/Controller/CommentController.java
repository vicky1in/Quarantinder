package com.groun24.quarantinder.Controller;

import com.groun24.quarantinder.Modal.Comment;
import com.groun24.quarantinder.Modal.CommentVo;
import com.groun24.quarantinder.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author vivian
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    List<CommentVo> findByPostId(@RequestParam Integer postId) {
        return commentService.findByPostId(postId);
    }

    @PostMapping
    void create(@RequestBody Comment comment) {
        commentService.create(comment);
    }

    @PutMapping
    void update(@RequestBody Comment comment) {
        commentService.update(comment);
    }

    @DeleteMapping("/{commentId}")
    void deleteById(@PathVariable Integer commentId) {
        commentService.deleteById(commentId);
    }
}
