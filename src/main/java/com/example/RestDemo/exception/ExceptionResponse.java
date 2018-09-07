package com.example.RestDemo.exception;

import java.util.Date;

public class ExceptionResponse {
	
	public ExceptionResponse(Date timestamp, String msg, String details) {
		super();
		this.timestamp = timestamp;
		this.msg = msg;
		this.details = details;
	}
	private Date timestamp;
	private String msg;
	private String details;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMsg() {
		return msg;
	}
	public String getDetails() {
		return details;
	}
}
