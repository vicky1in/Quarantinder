package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.Comment;
import com.groun24.quarantinder.Modal.CommentVo;

import java.util.List;

public interface CommentService {
    List<CommentVo> findByPostId(Integer postId);
    Comment create(Comment comment);
    void update(Comment comment);
    void deleteById(Integer commentId);
    void deleteByPostId(Integer postId);
}
