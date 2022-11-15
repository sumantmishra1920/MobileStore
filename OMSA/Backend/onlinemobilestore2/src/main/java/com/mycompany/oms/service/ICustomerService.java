package com.mycompany.oms.service;

import java.util.List;
import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.exceptions.CustomerNotFoundException;

//Customer service
public interface ICustomerService {

	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(int customerId,Customer Customer) throws CustomerNotFoundException;
	
	public Customer cancelCustomer(int customerId) throws CustomerNotFoundException;
	
	public List<Customer> showAllCustomers();
	
	public Customer show(int id) throws CustomerNotFoundException;
	
}
