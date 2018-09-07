package com.example.RestDemo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RestDemo.model.Post;
import com.example.RestDemo.model.User;

@Service
public class PostService {

	@Autowired
	private UserService userService;
	
	private Map<User,List<Post>> userPosts=new HashMap<>();

	private int postSequence=1;
	
	@PostConstruct
	public void initializePostService(){
		List<Post> posts = new ArrayList<>();
		Post post = new Post(1, "The final count down", "the dreadful impact of rapidly increasing oil demand on nature", new Date());
		posts.add(post);
		User user = userService.findOne(1);
		userPosts.put(user, posts);
	}
	
	public List<Post> findAllPostsByUserId(int id){
		for(Map.Entry<User, List<Post>> entry:userPosts.entrySet()){
			if(entry.getKey().getId() == id){
				return entry.getValue();
			}
		}
		return null;
	}
	
	public Post savePostForUser(int userId,Post post){
		
		User user = userService.findOne(userId);
		
		//check if the user exist
		if(user != null){
			boolean userExistsInMap = false;
			if(post.getId() == null){
				post.setId(++postSequence);
			}
			for(Map.Entry<User, List<Post>> entry:userPosts.entrySet()){
				if(entry.getKey().getId() == userId){
					userExistsInMap = true;
					entry.getValue().add(post);
				}
			}
			if(!userExistsInMap){
				List<Post> posts = new ArrayList<>();
				posts.add(post);
				userPosts.put(user,posts);
			}
			return post;
		}
		return null;
	}
	
	public Post findPostById(int userId,int postId){
		for(Map.Entry<User, List<Post>> entry:userPosts.entrySet()){
			if(entry.getKey().getId() == userId){
				for(Post post : entry.getValue()){
					if(post.getId() == postId){
						return post;
					}
				}
			}
		}
		return null;
	}
}
