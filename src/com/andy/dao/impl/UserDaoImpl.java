package com.andy.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.andy.dao.UserDao;
import com.andy.entity.User;
import com.andy.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Resource
	UserMapper userMapper;

	@Override
	public int addUser(User user) {
		return userMapper.addUser(user);
	}

}
