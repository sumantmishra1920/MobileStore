package com.mycompany.oms.service.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mycompany.oms.entities.User;

public class CustomUserDetails implements UserDetails{

	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	
	private static final long serialVersionUID = 1L;
	public CustomUserDetails(User user) {
		super();
		this.username = user.getUserName();
		this.password = user.getUserPassword();
		this.authorities = Arrays.stream(user.getUserRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;//isActive
	}

}