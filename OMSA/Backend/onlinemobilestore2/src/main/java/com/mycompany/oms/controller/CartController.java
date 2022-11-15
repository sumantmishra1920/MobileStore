package com.mycompany.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.oms.entities.Cart;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.entities.Order;
import com.mycompany.oms.exceptions.CartNotFoundException;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.service.ICartService;
import com.mycompany.oms.service.IOrderService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private ICartService cartService;
   
	@Autowired
	private IOrderService orderService;
	
	//To show list of all the carts
	//@PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/showAll")
    public ResponseEntity<List<Cart>> findAllCarts(){
    	return new ResponseEntity<>(cartService.showAll(), HttpStatus.OK); 
    }
	
	//To show list of all the mobiles present in the cart
	//@PreAuthorize("hasAuthority('Customer')")
    @GetMapping("/viewAllMobiles/{cartId}")
    public ResponseEntity<List<Mobile>> viewMobiles(@PathVariable("cartId") int cartId) throws CartNotFoundException{
    	return new ResponseEntity<>(cartService.viewMobiles(cartId), HttpStatus.OK);
    }
    
	//To show the cart info
	//@PreAuthorize("hasAuthority('Customer')")
    @GetMapping("/show/{cartId}")
    public ResponseEntity<Cart> viewCartInfo(@PathVariable("cartId") int cartId) throws CartNotFoundException{
    	return new ResponseEntity<>(cartService.viewCartInfo(cartId), HttpStatus.OK);
    }
    
	//To remove a specific mobile present in the cart
	//@PreAuthorize("hasAuthority('Customer')")
    @DeleteMapping("/remove/{cartId}/{mobileId}")
    public ResponseEntity<Cart> deleteMobile(@PathVariable("cartId") int cartId,@PathVariable("mobileId") int mobileId) throws CartNotFoundException, MobileNotFoundException{
    	return new ResponseEntity<>(cartService.deleteMobile(cartId, mobileId), HttpStatus.OK);
    }
	
	//To remove all the mobiles present in the cart
	//@PreAuthorize("hasAuthority('Customer')")
    @DeleteMapping("/removeAll/{cartId}")
    public ResponseEntity<Cart> deleteMobiles(@PathVariable("cartId") int cartId) throws CartNotFoundException{
    	return new ResponseEntity<>(cartService.deleteMobiles(cartId), HttpStatus.OK);
    }
    
	//To place an order for a mobile present in the cart
	//@PreAuthorize("hasAuthority('Customer')")
    @PostMapping("/placeOrder/{cartId}/{mobileId}")
    public ResponseEntity<Order> placeOrder(@PathVariable("cartId") int cartId,@PathVariable("mobileId") int mobileId) throws CartNotFoundException, MobileNotFoundException{
    	return new ResponseEntity<>(orderService.addOrder(cartId, mobileId), HttpStatus.CREATED);
    }
    
    
}
