package com.mycompany.oms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.entities.Order;
import com.mycompany.oms.exceptions.CustomerNotFoundException;
import com.mycompany.oms.exceptions.OrderNotFoundException;
import com.mycompany.oms.service.IOrderService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/order")
public class OrderController {
	
		@Autowired
		private IOrderService orderService;
		
		//To update an order
		//@PreAuthorize("hasAuthority('Admin')")
		@PutMapping("/update/{id}")
		public ResponseEntity<Order> updateOrder(@PathVariable("id") int id, @RequestBody Order order) throws OrderNotFoundException{
			return new ResponseEntity<Order>(orderService.updateOrder(id, order), HttpStatus.OK);
		}
		
		//To update status of an order
		//@PreAuthorize("hasAuthority('Admin')")
		@PutMapping("/updateStatus/{id}/{status}")
		public ResponseEntity<Order> updateStatus(@PathVariable("id") int id, @PathVariable("status") String status) throws OrderNotFoundException{
			return new ResponseEntity<>(orderService.updateOrderStatus(id, status), HttpStatus.OK);
		}
		
		//To update dispatch date of an order
		//@PreAuthorize("hasAuthority('Admin')")
		@PutMapping("/updateDispatchDate/{id}/{date}")
		public ResponseEntity<Order> updateDispatchDate(@PathVariable("id") int id, @PathVariable("date") String date) throws OrderNotFoundException{
			LocalDate dt=LocalDate.parse(date);
			return new ResponseEntity<>(orderService.updateDispatchDate(id, dt), HttpStatus.OK);
		}
		
		//To cancel an order
		//@PreAuthorize("hasAuthority('Customer')")
		@PutMapping("/cancel/{id}")
		public ResponseEntity<Order> cancelOrder(@PathVariable("id") int id) throws OrderNotFoundException{
			return new ResponseEntity<>(orderService.cancelOrder(id), HttpStatus.OK);
		}
		
		//To show information of mobile ordered
		@GetMapping("/showMobile/{id}")
		public ResponseEntity<Mobile> viewMobile(@PathVariable("id") int id) throws OrderNotFoundException{
			return new ResponseEntity<>(orderService.showMobile(id), HttpStatus.OK);
		}
		
		//To show order details
		@GetMapping("/show/{id}")
		public ResponseEntity<Order> viewOrder(@PathVariable("id") int id) throws OrderNotFoundException{
			return new ResponseEntity<>(orderService.showOrder(id), HttpStatus.OK);
		}
		
		//To show all orders
		//@PreAuthorize("hasAuthority('Admin')")
		@GetMapping("/showAll")
		public ResponseEntity<List<Order>> showAll(){
			return new ResponseEntity<>(orderService.showAllOrders(), HttpStatus.OK);
		}
		
		@GetMapping("/showbyCustomer/{id}")
		public ResponseEntity<List<Order>> viewOrdersByCustomer(@PathVariable("id") int id) throws CustomerNotFoundException{
			return new ResponseEntity<>(orderService.showOrderByCustomer(id), HttpStatus.OK);
		}
	

}
