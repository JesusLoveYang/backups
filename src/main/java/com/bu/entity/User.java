package com.bu.entity;

public class User {
	// 用户id
	private Long userId;
	// 用户名字
	private String userName; 
	// 用户密码
	private String userPassword; 
	// 角色id
	private Long rolesId;
	
	public Long getUserId() {

		return userId;
	}
	public void setUserId(Long userId) {

		this.userId = userId;
	}
	public String getUserName() {

		return userName;
	}
	public void setUserName(String userName) {

		this.userName = userName;
	}
	public String getUserPassword() {

		return userPassword;
	}
	public void setUserPassword(String userPassword) {

		this.userPassword = userPassword;
	}
	public Long getRolesId() {

		return rolesId;
	}
	public void setRolesId(Long rolesId) {

		this.rolesId = rolesId;
	}
	
	
}
