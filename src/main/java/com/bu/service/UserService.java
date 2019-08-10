package com.bu.service;

import com.bu.entity.User;

import java.util.Set;

public interface UserService {
	User queryUser(String username);

	Set<String> queryRoles(String username);
}
