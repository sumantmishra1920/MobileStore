package com.mycompany.oms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycompany.oms.entities.Cart;
import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.entities.Order;
import com.mycompany.oms.exceptions.CartNotFoundException;
import com.mycompany.oms.repository.ICartRepository;
import com.mycompany.oms.repository.IMobileRepository;
import com.mycompany.oms.repository.IOrderRepository;
import com.mycompany.oms.service.ICartService;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.oms.exceptions.MobileNotFoundException;

//Cart service implementation
@Service
public class CartServiceImpl implements ICartService{
	@Autowired
	ICartRepository cartRepository;
	
	@Autowired
	IMobileRepository mobileRepository;
	
	@Autowired
	IOrderRepository orderRepository;
	
	public CartServiceImpl(ICartRepository cartRepository, IMobileRepository mobileRepository,
			IOrderRepository orderRepository) {
		
		this.cartRepository = cartRepository;
		this.mobileRepository = mobileRepository;
		this.orderRepository = orderRepository;
	
	}

	//To add a cart for a customer
	@Override
	public Cart createCart(Customer customer) {

		int cartId = customer.getCustomerId();
		Cart c = new Cart();
		c.setCartId(cartId);
		c.setCustomer(customer);
		List<Mobile> m=new ArrayList<>();
		c.setMobiles(m);
		c.setQuantity(0);
		c.setCost(0);
		return cartRepository.save(c);
		
	}
	
	//To show list of all the carts
	@Override
	public List<Cart> showAll(){
		return cartRepository.findAll();
	}
	
	//To remove a specific mobile present in the cart
	@Override
	public Cart deleteMobile(int cartId, int mobileId) throws CartNotFoundException, MobileNotFoundException{
		Optional<Cart> c = cartRepository.findById(cartId);
		if(c.isPresent()) {
			Cart cart = c.get();
			Optional<Mobile> m = mobileRepository.findById(mobileId);
			if(m.isPresent()) {
				Mobile mobile = m.get();
				cart.getMobiles().remove(mobile);
				cart.setQuantity(cart.getQuantity() - 1);
				cart.setCost(cart.getCost() - (int)mobile.getMobileCost());
				cartRepository.save(cart);
				return cart;
			} else {
				throw new MobileNotFoundException("No mobile with given cartId present in cart");
			}
		} else {
			throw new CartNotFoundException("Cart not found with given cartId");
		}
		
	}

	//To remove all the mobiles present in the cart
	@Override
	public Cart deleteMobiles(int cartId) throws CartNotFoundException{
		Optional<Cart> c = cartRepository.findById(cartId);
		if(c.isPresent()) {
			Cart cart = c.get();
			cart.getMobiles().clear();
			cart.setQuantity(0);
			cart.setCost(0);
			cartRepository.save(cart);
			return cart;
		} else {
			throw new CartNotFoundException("Cart not found with given cartId");
		}
	}

	//To add a mobile to cart
	@Override
    public Cart addToCart(int cartId, int mobileId) throws MobileNotFoundException, CartNotFoundException{
		Optional<Cart> c = cartRepository.findById(cartId);
		if(c.isPresent()) {
			Cart cart = c.get();
			Optional<Mobile> m = mobileRepository.findById(mobileId);
			if(m.isPresent()) {
				Mobile mobile = m.get();
				//Checking if mobile is already present in other carts or in orders
				int flag = 0;
				List<Cart> carts = cartRepository.findAll();
				for(Cart ct: carts) {
					if(ct.getMobiles().contains(mobile)) {
						flag = 1;
						break;
					}
				}
				List<Order> orders = orderRepository.findAll();
				for(Order o:orders){
					if(o.getStatus().equals("Ordered")){
						if(o.getMobile().getMobileId()==mobileId)
						{
								flag = 1;
								break;
						}	
					}
				}
				
				if(flag==0) {
					cart.getMobiles().add(mobile);
					cart.setQuantity(cart.getQuantity() + 1);
					cart.setCost(cart.getCost() + (int)mobile.getMobileCost());
					cartRepository.save(cart);	
					return cart;
				} else {
					throw new MobileNotFoundException("Mobile with given id is not available for purchase");
				}	
			} else {
				throw new MobileNotFoundException("No mobile found with given mobileId");
			}
		} else {
			throw new CartNotFoundException("Cart not found with given cartId");
		}
	}

	//To show list of all the mobiles present in the cart
	@Override
	public List<Mobile> viewMobiles(int cartId) throws CartNotFoundException{
		Optional<Cart> c = cartRepository.findById(cartId);
		if(c.isPresent()) {
			Cart cart = c.get();
			return cart.getMobiles();
		} else {
			throw new CartNotFoundException("Cart not found with given cartId");
		}
	}

	//To show the cart info
	@Override
	public Cart viewCartInfo(int cartId) throws CartNotFoundException{
		return cartRepository.findById(cartId).orElseThrow(() -> 
		new CartNotFoundException("Cart not found with given cartId"));
	}
	
	//To add a number of same mobiles to the cart e.g. add 3 iPhone 11 to the cart
	@Override
    public Cart addMobiles(int cartId,String mobileName, int qty) throws MobileNotFoundException, CartNotFoundException{
		List<Mobile> mobiles = mobileRepository.findByMobileName(mobileName);
		if(mobiles.size()>0) {
			List<Cart> carts = cartRepository.findAll();
			//Counting the number of mobiles already present in other carts or in orders
			int count = 0;
			for(Cart ct:carts) {
				for(Mobile mob: ct.getMobiles()) {
					for(Mobile mo: mobiles) {
						if(mob.getMobileId() == mo.getMobileId())
							count++;
					}
				}
			}
			List<Order> orders= orderRepository.findAll();
			for(Order o:orders){
				for(Mobile mob: mobiles) {
					if(o.getMobile().getMobileId() == mob.getMobileId()){
						count++;
					}
				}
				
			}
			//Checking if the available quanitity is more than or equal to the given quanitity by the user
			if((mobileRepository.countByMobileName(mobileName) - count - qty) >= 0) {
				Optional<Cart> c = cartRepository.findById(cartId);
				if(c.isPresent()) {
					Cart cart = c.get();
					cart.getMobiles().addAll(mobiles);
					cart.setQuantity(cart.getQuantity()+qty);
					int individualCost = (int) mobiles.get(0).getMobileCost();
					int totalCost = individualCost * qty;
					cart.setCost(cart.getCost() + totalCost);
					cartRepository.save(cart);
					return cart; 
				} else {
					String s="Cart not found with id "+cartId;
					throw new CartNotFoundException(s);
				}
			} else {
				String s="Only "+(mobileRepository.countByMobileName(mobileName)-count)+" "+mobileName+" are availabe for purchase";
				throw new MobileNotFoundException(s);
			}
		} else {
			throw new MobileNotFoundException("No such mobile found with given name");
		}
	}
		
}
