package com.example.RestDemo.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


//use @ApiModel to add description to your model in the Swagger Documentation
@ApiModel("The users of the Social media API")
public class User {

	public User(){
		
	}
	
	public User(Integer id,String name,Date dob){
		super();
		this.id =id;
		this.name = name;
		this.dob = dob;
	}
	private Integer id;
	
	@Size(min=2,message="The name must be at least 2 chracters long.")
	@ApiModelProperty(notes="Name to be atleast 2 characters long")
	private String name;
	
	@Past(message="The date of birth must be lesser then today's date.")
	@ApiModelProperty(notes="Date of Birth should be in the past")
	private Date dob;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
}
