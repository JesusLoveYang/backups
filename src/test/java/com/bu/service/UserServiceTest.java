package com.bu.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bu.entity.User;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	String username = "yang";

	@Test
	public void testQueryUserAndRoles() {
		User user = userService.queryUser(username);
		Set<String> role = userService.queryRoles(username);

		System.out.println(user.getUserPassword() + "   " + role.toString());
	}
}
