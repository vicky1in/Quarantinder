package com.groun24.quarantinder.dao;

import com.groun24.quarantinder.Modal.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author vivian
 */
public interface PostDao extends CrudRepository<Post, Integer> {
    List<Post> findByUserIdOrderByTimePostedDesc(Integer UserID);
    List<Post> findAll();
    Post findByPostId(Integer postId);
}
