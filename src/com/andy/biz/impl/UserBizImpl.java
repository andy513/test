package com.andy.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andy.biz.UserBiz;
import com.andy.dao.UserDao;
import com.andy.entity.User;

@Service
public class UserBizImpl implements UserBiz{
	
	@Autowired
	UserDao userDao;

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

}
