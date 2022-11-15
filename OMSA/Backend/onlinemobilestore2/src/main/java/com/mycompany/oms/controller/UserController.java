package com.mycompany.oms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.oms.entities.User;
import com.mycompany.oms.exceptions.RecordExistsException;
import com.mycompany.oms.exceptions.UserNotFoundException;
import com.mycompany.oms.service.IUserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	
	//To show all users
	//@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("showAll")
	public ResponseEntity<List<User>> showAll(){
		return new ResponseEntity<>(userService.showAllUsers(), HttpStatus.OK);
	}
	
	//To register a new user
	@PostMapping("/register")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws RecordExistsException{
		return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
	}
	
	//To update a user
	//@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id,@Valid @RequestBody User user) throws UserNotFoundException{
		return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
	}
	
	//To remove a user
	//@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) throws UserNotFoundException{
		return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);
	}
	
	//To validate credentials of a user
	@GetMapping("validate")
	public ResponseEntity<String> validateUser(@RequestParam("name") String name, @RequestParam("password") String password) throws UserNotFoundException{
		if(userService.validateUser(name, password)) {
			String s="Correct Credentials";
			return new ResponseEntity<>(s, HttpStatus.OK);
		} else {
			String s="Wrong credentials";
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
	}
}
