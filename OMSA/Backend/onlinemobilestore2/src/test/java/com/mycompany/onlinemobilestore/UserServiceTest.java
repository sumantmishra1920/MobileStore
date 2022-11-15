package com.mycompany.onlinemobilestore;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mycompany.oms.entities.User;
import com.mycompany.oms.exceptions.CustomerNotFoundException;
import com.mycompany.oms.exceptions.RecordExistsException;
import com.mycompany.oms.exceptions.UserNotFoundException;
import com.mycompany.oms.repository.IUserRepository;
import com.mycompany.oms.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private IUserRepository repo;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	
	private User u1;
	
	@BeforeEach
	void setUp() {
		repo=Mockito.mock(IUserRepository.class);
		userService=new UserServiceImpl(repo, new BCryptPasswordEncoder());
		u1=new User(1,"aravind","1111111","customer");
		
	}
	
	@Test
	void testAddUser() throws RecordExistsException {
		when(repo.save(u1)).thenReturn(u1);
		User savedUser=userService.addUser(u1);
		assertNotNull(savedUser);
	}
	
	@Test
	void testAddUserExists() throws RecordExistsException {
		when(repo.findByUserName(u1.getUserName())).thenReturn(u1);
		assertThrows(RecordExistsException.class, ()->{
			userService.addUser(u1);
		});
	}
	
	@Test
	void testRemoveUser() throws UserNotFoundException {
		given(repo.findById(1)).willReturn(Optional.of(u1));
		User removeUser=userService.removeUser(1);
		assertNotNull(removeUser);
		assertThrows(UserNotFoundException.class, ()->{
			userService.removeUser(9);
		});
	}
	
	@Test
	void testShowAllUsers() {
		User u2=new User(2,"micheal","miche@789","admin");
		User u3=new User(3,"sumant","sumant@456","customer");
		User u4=new User(4,"kanha","kanha@876","admin");
		User u5=new User(5,"monica","monica@092","customer");
		User u6=new User(6,"manvi","manvi@4568","customer");
		when(repo.findAll()).thenReturn(List.of(u1,u2,u3,u4,u5,u6));
		List<User> mList= userService.showAllUsers();
		assertNotNull(mList);
		assertEquals(6,mList.size());
	}
	
	@Test
	void testUpdateUser() throws UserNotFoundException  {
		given(repo.findById(1)).willReturn(Optional.of(u1));
		u1.setUserName("abhilash");
		u1.setUserPassword("abhil@345");
		User updatedUser = userService.updateUser(1, u1);
		assertEquals("abhilash",updatedUser.getUserName());
		assertEquals("abhil@345",updatedUser.getUserPassword());
		assertThrows(UserNotFoundException.class, ()->{
			userService.updateUser(9,u1);
		});
	}
	
	@Test 
	void testValidateUser() throws UserNotFoundException{
		given(repo.findById(1)).willReturn(Optional.of(u1));
		assertTrue(userService.validateUser("aravind", "1111111"));
		assertThrows(UserNotFoundException.class, ()->{
			userService.validateUser("aravind", "1111111");
		});
	}
}
