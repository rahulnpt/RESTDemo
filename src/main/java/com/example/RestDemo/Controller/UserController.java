package com.example.RestDemo.Controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.RestDemo.exception.UserNotFoundException;
import com.example.RestDemo.model.User;
import com.example.RestDemo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/users",method=RequestMethod.GET,produces={ "application/json" ,"application/xml"}, consumes = MediaType.ALL_VALUE)
	public List<User> getAllUsers(){
		return userService.findAll();
	}
	@RequestMapping(value="/users/{user_id}",method=RequestMethod.GET)
	public User getUser(@PathVariable("user_id") int userId){
		User user = userService.findOne(userId);
		if(user == null){
			throw new UserNotFoundException("User not found");
		}
		return user;
	}
	@RequestMapping(value="/users",method=RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
		User savedUser = userService.saveUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//for delete we can keep the return type as void. This will ensure that if everything 
	//goes fine then 200 status code is returned, so we have two ways of doing this, 
	//either return void or use ResponseEntity.noContent() method and return the ResponseEntity object.
	@RequestMapping(value="/users/{id}",method=RequestMethod.DELETE)
	public void deleteUserById(@PathVariable int id){
		User deletedUser = userService.deleteById(id);
		if(deletedUser == null){
			throw new UserNotFoundException("User Not Found");
		}
	}
}
