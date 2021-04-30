package com.groun24.quarantinder.dao;

import com.groun24.quarantinder.Modal.Comment;
import com.groun24.quarantinder.Modal.CommentVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author vivian
 */
public interface CommentDao extends CrudRepository<Comment, Integer> {
//    @Query(nativeQuery = true, value = "select c.comment_body, c.time_posted, u.name from comment c, user u where c.post_id = ?1 and c.user_id = u.userid")
    @Query(value = "select new com.groun24.quarantinder.Modal.CommentVo(c.commentBody, u.Name, c.timePosted) from Comment c, User u where c.postId = ?1 and c.userId = u.UserID")
    List<CommentVo> findByPostIdOrderByTimePostedDesc(Integer postId);
    void deleteByPostId(Integer postId);
}
