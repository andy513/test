package com.andy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.andy.entity.User;

public interface UserMapper {
	
	int addUsers(List<User> users);
	
	int modifyUsers(List<User> users);
	
	int addUser(User user);
	
	int modifyUser(User user);
	
	User selUser(User user);
	
	User selUser(@Param(value="uname")String uname,@Param(value="pwd")String pwd);
	
	List<User> selUsers();

}
