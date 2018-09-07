package com.example.RestDemo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.RestDemo.model.User;

@Service
public class UserService {

	private List<User> userList = new ArrayList<>(Arrays.asList(new User(1,"Rahul",new Date()),
																new User(2,"Krishna",new Date()),
																new User(3,"Balram",new Date()),
																new User(4,"Surya",new Date())));
	private int idSequence=4;
	
	public List<User> findAll(){
		return userList;
	}
	
	public User findOne(int id){
		for(User user: userList){
			if(user.getId() == id){
				return user;
			};
		}
		return null;
	}
	public User saveUser(User user){
		if(user.getId()==null){
			user.setId(++idSequence);
		};
		userList.add(user);
		return user;
	}
	public User deleteById(int id){
		User user = findOne(id);
		if(user != null){
			userList.remove(user);
			return user;
		}
		return null;
	}
}
