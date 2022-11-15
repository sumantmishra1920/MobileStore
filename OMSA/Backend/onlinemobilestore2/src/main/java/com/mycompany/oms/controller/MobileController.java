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

import com.mycompany.oms.service.ICartService;
import com.mycompany.oms.service.IMobileService;
import com.mycompany.oms.entities.Cart;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.exceptions.CartNotFoundException;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.exceptions.NoSuchCategoryException;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/mobile")
public class MobileController {
	
	@Autowired
	private IMobileService mobileService;
	
	@Autowired
	private ICartService cartService;
	
	//To add a new mobile to the database
	//@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/add")
	public ResponseEntity<Mobile> addMobile(@Valid @RequestBody Mobile mobile){
		return new ResponseEntity<>(mobileService.addMobile(mobile), HttpStatus.CREATED);
	}
	
	//To update a specific mobile's details
	//@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Mobile> updateMobile(@PathVariable("id") int id,@Valid @RequestBody Mobile mobile) throws MobileNotFoundException{
		return new ResponseEntity<>(mobileService.updateMobile(id, mobile), HttpStatus.OK);
	}
	
	//To remove a mobile from database
	//@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Mobile> deleteMobile(@PathVariable("id") int id) throws MobileNotFoundException{
		return new ResponseEntity<>(mobileService.deleteMobile(id), HttpStatus.OK);
	}
	
	//To show list of all mobiles
	@GetMapping("/showAll")
	public ResponseEntity<List<Mobile>> showAll(){
		return new ResponseEntity<>(mobileService.showAllMobiles(), HttpStatus.OK);
	}
	
	//To show details of a specific mobile
	@GetMapping("/show/{id}")
	public ResponseEntity<Mobile> showById(@PathVariable("id") int id) throws MobileNotFoundException{
		return new ResponseEntity<>(mobileService.showMobileById(id), HttpStatus.OK);
	}
	
	//To get list of all the same mobiles that are available in store
	@GetMapping("/byName/{name}")
	public ResponseEntity<List<Mobile>> showAllByName(@PathVariable("name") String name){
		return new ResponseEntity<>(mobileService.showMobilesByName(name), HttpStatus.OK);
	}
	
	//To check the available quantity of a specific mobile in stock
	@GetMapping("/count/{name}")
	public ResponseEntity<String> countAllByName(@PathVariable("name") String name){
		String s=mobileService.availableQuantity(name)+" "+name+" are available in store";
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	//To show a list of mobiles that are available in the specific price range
	@GetMapping("/byPriceRange")
	public ResponseEntity<List<Mobile>> showAllByPriceRange(@RequestParam("price1") int price1, @RequestParam("price2") int price2){
		return new ResponseEntity<>(mobileService.showMobilesByPriceRange(price1, price2), HttpStatus.OK);
	}
	
	//To show a list of mobiles belonging to a specific category
	@GetMapping("/showByCategory/{id}")
	public ResponseEntity<List<Mobile>> showByCategory(@PathVariable("id") int id) throws NoSuchCategoryException{
		return new ResponseEntity<>(mobileService.showMobilesByCategory(id), HttpStatus.OK);
	}
	
	//To show a list of mobiles from a specific company
	@GetMapping("/showByCompany/{name}")
	public ResponseEntity<List<Mobile>> showAllByCompanyName(@PathVariable("name") String name){
		return new ResponseEntity<>(mobileService.showMobilesByCompany(name), HttpStatus.OK);
	}
	
	//To add a mobile to cart
	//@PreAuthorize("hasAuthority('Customer')")
	@PutMapping("/addToCart/{cartId}/{mobileId}")
    public ResponseEntity<Cart> addToCart(@PathVariable("cartId") int cartId, @PathVariable("mobileId") int mobileId) throws CartNotFoundException, MobileNotFoundException {
		return new ResponseEntity<>(cartService.addToCart(cartId,mobileId), HttpStatus.OK);
    }
	
	//To add a number of same mobiles to the cart e.g. add 3 iPhone 11 to the cart
	//@PreAuthorize("hasAuthority('Customer')")
	@PutMapping("/addMobiles/{cartId}/{name}/{qty}")
	public ResponseEntity<Cart> addMobilesToCart(@PathVariable("cartId") int cartId, @PathVariable("name") String name, @PathVariable("qty") int qty) throws MobileNotFoundException, CartNotFoundException{
		return new ResponseEntity<>(cartService.addMobiles(cartId, name, qty), HttpStatus.OK);
	}
}
