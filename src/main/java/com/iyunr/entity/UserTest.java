package com.iyunr.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserTest {
	private String id;
	@NotNull
	@Size(min=3, max=5, message="字符应该再3-5之间")
	private String name;
	@Max(value=120, message="年龄最大不能超过120岁")
	private Integer age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
