package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.Post;
import com.groun24.quarantinder.dao.CommentDao;
import com.groun24.quarantinder.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author vivian
 */
@Service
public class PostServiceImpl implements PostService {

    private final PostDao postDao;
    private final CommentDao commentDao;

    @Autowired
    public PostServiceImpl(PostDao postDao, CommentDao commentDao) {
        this.postDao = postDao;
        this.commentDao = commentDao;
    }

    @Override
    public List<Post> findByUserID(Integer UserID) {
        return postDao.findByUserIdOrderByTimePostedDesc(UserID);
    }

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }

    @Override
    public Post create(Post post) {
        post.setTimePosted(new Timestamp(System.currentTimeMillis()));
        System.out.println(new Timestamp(System.currentTimeMillis()));
        return postDao.save(post);
    }

    @Override
    public void update(Post post) {
        postDao.save(post);
    }

    @Override
    @Transactional
    public void deleteById(Integer postId) {
        commentDao.deleteByPostId(postId);
        postDao.deleteById(postId);
    }

    @Override
    public Post findByPostId(Integer postId) {
        return postDao.findByPostId(postId);
    }
}
