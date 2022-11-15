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
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.exceptions.CustomerNotFoundException;
import com.mycompany.oms.service.ICartService;
import com.mycompany.oms.service.ICustomerService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ICartService cartService;
	
	//To register customer
	//@PreAuthorize("hasAuthority('Customer')")
	@PostMapping("/register")
	public  ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		Customer c=customerService.addCustomer(customer);
		//To create the cart along with customer registration
		cartService.createCart(customer);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	
	//To update customer details
	//@PreAuthorize("hasAuthority('Customer')")
	@PutMapping("/update/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId,@Valid @RequestBody Customer customer) throws CustomerNotFoundException{
		Customer c1=customerService.updateCustomer(customerId,customer);
		return new ResponseEntity<>(c1, HttpStatus.OK);
	}
	
	//To remove a customer from the database
	//@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/remove/{customerId}")
	public ResponseEntity<Customer> cancelCustomer(@PathVariable int customerId) throws CustomerNotFoundException{
		Customer c1=customerService.cancelCustomer(customerId);
		return new ResponseEntity<>(c1, HttpStatus.OK);
	}
	
	//To show list of all customers
	//@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/showAll")
	public ResponseEntity<List<Customer>> showAllCustomers() {
		return new ResponseEntity<>(customerService.showAllCustomers(), HttpStatus.OK);
	}
	
	//To show details of a specific customer
	@GetMapping("/show/{id}")
	public ResponseEntity<Customer> showById(@PathVariable("id") int id) throws CustomerNotFoundException{
		return new ResponseEntity<>(customerService.show(id), HttpStatus.OK);
	}

}