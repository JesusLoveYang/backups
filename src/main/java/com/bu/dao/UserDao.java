package com.bu.dao;

import com.bu.entity.User;

import java.util.Set;

public interface UserDao {
	// 获取用户信息
	User getUserByUsername(String username);
	
	// 获取用户的角色
	Set<String> getRolesByusername(String username);

	// 获取任务列表

}
