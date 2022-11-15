package com.mycompany.oms.service;

import java.util.List;

import com.mycompany.oms.entities.User;
import com.mycompany.oms.exceptions.RecordExistsException;
import com.mycompany.oms.exceptions.UserNotFoundException;

//User service
public interface IUserService {
	
	public User addUser(User user) throws RecordExistsException;
	
	public User updateUser(int userId,User user) throws UserNotFoundException;
	
	public User removeUser(int userId) throws UserNotFoundException;
	
	public List<User> showAllUsers();
	
	public boolean validateUser(String userName, String userPassword) throws UserNotFoundException;
}
