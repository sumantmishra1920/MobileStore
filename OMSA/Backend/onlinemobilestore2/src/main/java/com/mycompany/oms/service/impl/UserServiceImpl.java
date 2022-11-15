package com.mycompany.oms.service.impl;

import org.springframework.stereotype.Service;

import com.mycompany.oms.service.IUserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mycompany.oms.common.UserConstant;
import com.mycompany.oms.entities.User;
import com.mycompany.oms.exceptions.RecordExistsException;
import com.mycompany.oms.exceptions.UserNotFoundException;
import com.mycompany.oms.repository.IUserRepository;

//User service implementation
@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	//To register a new user
	@Override
	public User addUser(User user) throws RecordExistsException {
		//checking if username is already taken
		if(userRepository.findByUserName(user.getUserName())!=null) {
			throw new RecordExistsException("Username " + user.getUserName()+" is already taken");
		}
		
		if(user.getUserRole().equals("Customer"))
			user.setUserRole(UserConstant.DEFAULT_ROLE);//CUSTOMER
		else
			user.setUserRole(UserConstant.ADMIN_ACCESS);//ADMIN
		//encrpting password before storing
		String encryptedPwd = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encryptedPwd);
        return userRepository.save(user);
	}
	
	//To show all users
	@Override
	public List<User> showAllUsers() {
		return userRepository.findAll();
	}

	//To remove a user
	@Override
	public User removeUser(int userId) throws UserNotFoundException {
		Optional<User> opt=userRepository.findById(userId);
		if(opt.isPresent()) {
			userRepository.deleteById(userId);
		} else {
			throw new UserNotFoundException("User not found in the Database");
		}
		return opt.get();
	}
	
	//To update a user
	@Override
	public User updateUser(int userId,User user)throws UserNotFoundException {
		Optional<User> opt=userRepository.findById(userId);
		if(opt.isPresent()) {
			User existingUser=opt.get();
			existingUser.setUserName(user.getUserName());
			String encryptedPwd = passwordEncoder.encode(user.getUserPassword());
	        existingUser.setUserPassword(encryptedPwd);
			existingUser.setUserRole(user.getUserRole());
			userRepository.save(existingUser);
			return existingUser;
		} else {
			throw new UserNotFoundException("User not found in the DataBase");
		}
	}
	
	//To validate credentials of a user
	@Override
	public boolean validateUser(String userName, String userPassword) throws UserNotFoundException {
		Optional<User> opt=Optional.ofNullable(userRepository.findByUserName(userName));
		if(opt.isPresent()) {
			User existingUser = opt.get();
			boolean isPassword= passwordEncoder.matches(userPassword, existingUser.getUserPassword());
			if(existingUser.getUserName().equals(userName)&&isPassword)
				return true;
			else
				return false;
		} else {
			throw new UserNotFoundException("User not found in the DataBase");
		}
	}
}
