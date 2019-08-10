package com.bu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bu.entity.User;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	String username = "yang";
			
	@Test
	public void testGetUserByUsername() {
		User user = userDao.getUserByUsername(username);
		System.out.println(user.getRolesId());
		
		Set<String> rolesname = userDao.getRolesByusername(username);
		System.out.println(rolesname.toString());
	}
}
