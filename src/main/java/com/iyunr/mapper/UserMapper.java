package com.iyunr.mapper;

import java.util.List;

import com.iyunr.entity.User;

public interface UserMapper {
	public List<User> getAll();

	public List<User> likeUserName(String name);
	
	//新增用户
	public int insertUser(User user);
	
	//将用户改成已取消关注用户
	public int uptUsertoUnSubscribe(String openid);
	
	public int uptUserById(User user);
	
	public int delUserById(String id);
}
