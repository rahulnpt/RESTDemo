package com.example.RestDemo.exception;

public class PostsNotFoundException extends RuntimeException {

	public PostsNotFoundException(String msg) {
		super(msg);
	}

}
