package com.mycompany.oms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.exceptions.CustomerNotFoundException;
import com.mycompany.oms.repository.ICustomerRepository;
import com.mycompany.oms.service.ICustomerService;

//Customer service implementation
@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	public CustomerServiceImpl(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	//To register customer
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	//To update customer details
	@Override
	public Customer updateCustomer(int customerId,Customer customer) throws CustomerNotFoundException {
		
		Optional<Customer> c = customerRepository.findById(customerId);
		if (c.isPresent()) {
			Customer cust = c.get();
			cust.setCustomerName(customer.getCustomerName());
			cust.setAddress(customer.getAddress());
			cust.setEmailId(customer.getEmailId());
			cust.setMobileNumber(customer.getMobileNumber());
			customerRepository.save(cust);
			return cust;
		} else 
			throw new CustomerNotFoundException("There is a problem in updation");
	}
	
	//To remove a customer from the database
	@Override
	public Customer cancelCustomer(int customerId) throws CustomerNotFoundException {
		Optional<Customer> c = customerRepository.findById(customerId);
		if(c.isPresent()) {
			customerRepository.deleteById(customerId);
		} else {
			throw new CustomerNotFoundException("Customer not found in the Database");
		}
		return c.get();
	}

	//To show list of all customers
	@Override
	public List<Customer> showAllCustomers() {
		return customerRepository.findAll();
	}

	//To show details of a specific customer
	@Override
	public Customer show(int id) throws CustomerNotFoundException {
		Optional<Customer> opt = customerRepository.findById(id);
		if(opt.isPresent()) {
			Customer cust = opt.get();
			return cust;
		} else {
			throw new CustomerNotFoundException("Customer not found in the Database");
		}
		
	}

}
