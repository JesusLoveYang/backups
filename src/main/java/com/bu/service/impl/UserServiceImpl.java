package com.bu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bu.dao.UserDao;
import com.bu.entity.User;
import com.bu.service.UserService;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User queryUser(String username) {

	    return userDao.getUserByUsername(username);
	}

	@Override
	public Set<String> queryRoles(String username) {
		return userDao.getRolesByusername(username);
	}

}
