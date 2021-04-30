package com.groun24.quarantinder;


import com.groun24.quarantinder.Modal.Comment;
import com.groun24.quarantinder.Modal.Post;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Services.CommentService;
import com.groun24.quarantinder.Services.PostService;
import com.groun24.quarantinder.Services.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootTest
class QuarantinderApplicationTests {
	@Autowired private PostService postService;
	@Autowired private UserService userService;
	@Autowired private CommentService commentService;

	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	void postTest() {
		User user = new User();
		user.setPassword("Mark");
		user.setName("Mark");
		user.setUsername("Mark");
		user = userService.save(user);
		Assert.isTrue(user != null, "failed to save user");

		Integer userId = user.getId();
		String postBody = "hello world";

		// create
		Post post = new Post();
		post.setUserId(userId);
		post.setPostBody(postBody);
		Post savedPost = postService.create(post);
		Assert.isTrue(savedPost != null, "failed to save");
		Assert.isTrue(savedPost.getPostBody().equals(postBody), "save error");

		// findByUserId
		List<Post> posts = postService.findByUserID(savedPost.getUserId());
		for (Post p : posts) {
			System.out.println(p.getPostBody());
		}

		// update
		String newPostBody = "hello newpost";
		savedPost.setPostBody(newPostBody);
		postService.update(savedPost);
		savedPost = postService.findByPostId(savedPost.getPostId());
		Assert.isTrue(savedPost.getPostBody().equals(newPostBody), "update error");

		// delete
		postService.deleteById(savedPost.getPostId());
		savedPost = postService.findByPostId(savedPost.getPostId());
		Assert.isTrue(savedPost == null, "failed to delete");

	}

	@Test
	void userTest() {
		String userName = "Tom";
		String email = "1111@gmail.com";
		String phoneNumber = "11111";
		String gender = "Male";
		String name = "Tom";
		String passWord = "123456";

		User user = new User();
		user.setPassword(passWord);
		user.setUsername(userName);
		user.setEmail(email);
		user.setPhonenumber(phoneNumber);
		user.setGender(gender);
		user.setName(name);

		// save
		user = userService.save(user);
		System.out.println(user.getId());
		Assert.isTrue(user != null, "failed to save");
		Assert.isTrue(user.getUsername().equals(userName), "save error");

		// findByUserName
		user = userService.findByUsername(userName);
		Assert.isTrue(user != null, "failed to find");

		// update password
		System.out.println("old password: " + user.getPassword());
		user.setPassword("456123");
		userService.updatePassword(user);
		user = userService.findByUsername(userName);
		System.out.println("new password: " + user.getPassword());

		// findByEmail
		user = userService.findByEmail(user.getEmail());
		Assert.isTrue(user != null, "failed to find by email");

		// findByPhoneNumber
		user = userService.findByPhonenumber(phoneNumber);
		Assert.isTrue(user != null, "failed to find by phonenumber");

		// delete
		userService.delete(user.getId());
		user = userService.findByUsername(user.getUsername());
		Assert.isTrue(user == null, "failed to delete");
	}

	@Test
	void commentTest() {
		String commentBody = "hello";
		User user = new User();
		user.setName("Tom");
		user.setPassword("Tom");
		user = userService.save(user);
		Assert.isTrue(user != null, "failed to save user");

		Post post = new Post();
		post.setPostBody("hello");
		post.setUserId(user.getId());
		post = postService.create(post);
		Assert.isTrue(post != null, "failed to create post");

		Comment comment = new Comment();
		comment.setCommentBody(commentBody);
		comment.setPostId(post.getPostId());
		comment.setUserId(user.getId());

		//create
		comment = commentService.create(comment);
		Assert.isTrue(comment != null && comment.getCommentBody() == commentBody
				&& comment.getUserId() == user.getId() && comment.getPostId() == post.getPostId(), "create error");


		//update
		//deleteById
	}

}
