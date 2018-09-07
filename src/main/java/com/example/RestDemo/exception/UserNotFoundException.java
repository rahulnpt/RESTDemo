package com.example.RestDemo.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String msg){
		super(msg);
	}
}
