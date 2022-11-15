package com.mycompany.oms.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.oms.entities.User;
import com.mycompany.oms.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private IUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=Optional.ofNullable(repository.findByUserName(username));
		return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException(username+" does not exist"));

	}
}