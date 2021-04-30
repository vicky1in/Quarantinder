package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.Post;

import java.util.List;

/**
 * @author vivian
 */
public interface PostService {
    List<Post> findByUserID(Integer UserID);
    List<Post> findAll();
    Post findByPostId(Integer postId);
    Post create(Post post);
    void update(Post post);
    void deleteById(Integer postId);
}
