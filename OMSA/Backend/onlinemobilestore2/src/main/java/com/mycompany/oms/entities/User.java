package com.mycompany.oms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//User entity
@Entity
@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id",nullable=false)
	private int userId;
	@Column(name="user_name",nullable=false)
	@NotEmpty(message="User name cannot be blank")
	@Size(min=2, message="User name should be atleast 2 characters long")
	private String userName;
	@Column(name="user_password",nullable=false)
	@NotEmpty(message="Password cannot be blank")
	@Size(min=8, message="Password should be atleast 8 characters long")
	private String userPassword;
	@Column(name="user_role",nullable=false)
	@Pattern(regexp="Admin|Customer", message="Role can be either Admin or Customer")
	private String userRole;
	
	public User() {
		
	}
	public User(int userId, String userName, String userPassword, String userRole) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userRole="
				+ userRole + "]";
	}
	
	
	
}
