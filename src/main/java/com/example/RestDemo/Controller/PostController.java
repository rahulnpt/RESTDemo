package com.example.RestDemo.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.RestDemo.exception.PostsNotFoundException;
import com.example.RestDemo.exception.UserNotFoundException;
import com.example.RestDemo.model.Post;
import com.example.RestDemo.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(method=RequestMethod.GET,value="/users/{userId}/posts")
	public List<Post> findPostsByUser(@PathVariable("userId") int userId){
		List<Post> allPosts = postService.findAllPostsByUserId(userId);
		if(allPosts == null){
			throw new PostsNotFoundException("No Posts founds");
		}
		return allPosts;
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/users/{userId}/posts/{postId}")
	public Post findPostsByPostId(@PathVariable("userId") int userId,@PathVariable("postId") int postId){
		Post post = postService.findPostById(userId, postId);
		if(post == null){
			throw new PostsNotFoundException("No Posts founds");
		}
		return post;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/users/{userId}/posts")
	public ResponseEntity<Object> savePost(@PathVariable("userId") int userId,@RequestBody Post post){
		Post savedPost = postService.savePostForUser(userId, post);
		if(savedPost == null){
			throw new UserNotFoundException("User not found");
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
