package com.groun24.quarantinder.Services;

import com.groun24.quarantinder.Modal.Comment;
import com.groun24.quarantinder.Modal.CommentVo;
import com.groun24.quarantinder.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author vivian
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<CommentVo> findByPostId(Integer postId) {
//        System.out.println(commentDao.findByPostIdOrderByTimePostedDesc(postId));
        return commentDao.findByPostIdOrderByTimePostedDesc(postId);
    }

    @Override
    public Comment create(Comment comment) {
        comment.setTimePosted(new Timestamp(System.currentTimeMillis()));
        return commentDao.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public void deleteById(Integer commentId) {
        commentDao.deleteById(commentId);
    }

    @Override
    public void deleteByPostId(Integer postId) {
        commentDao.deleteById(postId);
    }
}
