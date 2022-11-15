package com.mycompany.onlinemobilestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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


import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.exceptions.CustomerNotFoundException;
import com.mycompany.oms.exceptions.NoSuchCategoryException;
import com.mycompany.oms.repository.ICustomerRepository;
import com.mycompany.oms.repository.IMobileRepository;
import com.mycompany.oms.service.impl.CustomerServiceImpl;
import com.mycompany.oms.service.impl.MobileServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerServiceImpl custService;
	
	@Mock
	private ICustomerRepository customerRepository;
	
	
	private Customer c1;
	
	@BeforeEach
	void setUp() {
		customerRepository=Mockito.mock(ICustomerRepository.class);
		custService=new CustomerServiceImpl(customerRepository);
		c1=new Customer(1,"aravind","ms12345@gmail.com",7588446575L,"Bangalore");
	}
	
	@Test
	void testAddCustomer() {
		when(customerRepository.save(c1)).thenReturn(c1);
		Customer savedCustomer=custService.addCustomer(c1);
		assertNotNull(savedCustomer);
	}
	
	@Test
	void testUpdateCustomer() throws CustomerNotFoundException {
		given(customerRepository.findById(1)).willReturn(Optional.of(c1));
		c1.setCustomerName("Ande");
		c1.setAddress("Delhi");
		Customer updatedCustomer;
		updatedCustomer = custService.updateCustomer(1, c1);
		assertEquals("Ande",updatedCustomer.getCustomerName());
		assertEquals("Delhi",updatedCustomer.getAddress());
		
		assertThrows(CustomerNotFoundException.class, ()->{
			custService.updateCustomer(9,c1);
		});
	}
	
	@Test
	void testShowAllCustomers() {
		Customer c2=new Customer(2,"monica","mc12345@gmail.com",7784534645L,"Bangalore");
		Customer c3=new Customer(3,"manvi","mch12345@gmail.com",77845365645L,"nagpur");
		Customer c4=new Customer(4,"kanha","mcsl12345@gmail.com",7784509845L,"delhi");
		Customer c5=new Customer(5,"sumant","mcfjkg12345@gmail.com",7094534645L,"Bombay");
		
		when(customerRepository.findAll()).thenReturn(List.of(c1,c2,c3,c4,c5));
		List<Customer> cList= custService.showAllCustomers();
		assertNotNull(cList);
		assertEquals(5,cList.size());
	}
	
	@Test
	void testDeleteCustomer() throws CustomerNotFoundException {
		given(customerRepository.findById(1)).willReturn(Optional.of(c1));
		Customer deletedCustomer=custService.cancelCustomer(1);
		
		assertNotNull(deletedCustomer);
		assertThrows(CustomerNotFoundException.class, ()->{
			custService.cancelCustomer(9);
		});
	}
	
	@Test
	void testShow() throws CustomerNotFoundException {
		given(customerRepository.findById(1)).willReturn(Optional.of(c1));
		Customer cust=custService.show(1);
		
		assertEquals("aravind",cust.getCustomerName());
		assertEquals("Bangalore",cust.getAddress());
		assertEquals("ms12345@gmail.com",cust.getEmailId());
		assertEquals(7588446575L,cust.getMobileNumber());
		
		assertThrows(CustomerNotFoundException.class, ()->{
			custService.show(9);
		});
	}
	
	
}
