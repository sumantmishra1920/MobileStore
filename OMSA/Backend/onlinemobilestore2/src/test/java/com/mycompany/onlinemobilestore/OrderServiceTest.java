package com.mycompany.onlinemobilestore;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.oms.entities.Cart;
import com.mycompany.oms.entities.Category;
import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.entities.Order;
import com.mycompany.oms.exceptions.CartNotFoundException;
import com.mycompany.oms.exceptions.CustomerNotFoundException;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.exceptions.OrderNotFoundException;
import com.mycompany.oms.repository.ICartRepository;
import com.mycompany.oms.repository.ICategoryRepository;
import com.mycompany.oms.repository.ICustomerRepository;
import com.mycompany.oms.repository.IMobileRepository;
import com.mycompany.oms.repository.IOrderRepository;
import com.mycompany.oms.service.impl.CartServiceImpl;
import com.mycompany.oms.service.impl.OrderServiceImpl;

class OrderServiceTest {
	@Mock
	private IOrderRepository orderRepository;
	
	@Mock
	private ICartRepository cartRepository;
	
	@Mock
	private ICustomerRepository customerRepository;
	
	@Mock
	private IMobileRepository mobileRepository;
	
	@InjectMocks
	private OrderServiceImpl orderService;
	@InjectMocks
	private CartServiceImpl cartService;
	
	private Order o1;
	private Mobile m1;
	private Customer cust1;
	private Cart c1;
	
	@BeforeEach
	void setUp() {
		mobileRepository= Mockito.mock(IMobileRepository.class);
		cartRepository= Mockito.mock(ICartRepository.class);
		customerRepository= Mockito.mock(ICustomerRepository.class);
		orderRepository= Mockito.mock(IOrderRepository.class);
		cartService=new CartServiceImpl(cartRepository, mobileRepository, orderRepository);
		orderService= new OrderServiceImpl(orderRepository,cartRepository,customerRepository,mobileRepository, cartService);
		m1= new Mobile(1,"Redmi 4",7800.0f,LocalDate.of(2018, 9, 12),"1111111","Xiaomi",new Category(3,"Smart Phone"));
		cust1= new Customer(1,"aravind","ms12345@gmail.com",7588446575L,"Bangalore");
		List<Mobile> mList=new ArrayList<Mobile>();
		mList.add(m1);
		c1= new Cart(1,cust1,mList,1,7800);
		o1= new Order(1,LocalDate.of(2022, 8, 9),LocalDate.of(2022, 8, 10),7800,7900,"Ordered",cust1,m1);
		
	}
	
	@Test
	void testAddOrder() throws CartNotFoundException, MobileNotFoundException {
		given(cartRepository.findById(1)).willReturn(Optional.of(c1));
		given(mobileRepository.findById(1)).willReturn(Optional.of(m1));
		when(orderRepository.save(o1)).thenReturn(o1);
		when(cartRepository.save(c1)).thenReturn(c1);
		Order savedOrder=orderService.addOrder(1, 1);
		assertNotNull(savedOrder);
		assertThrows(CartNotFoundException.class, ()->{
			orderService.addOrder(4,1);
		});
		assertThrows(MobileNotFoundException.class, ()->{
			orderService.addOrder(1,4);
		});
	}
	
	@Test
	void testUpdateOrder() throws OrderNotFoundException{
		given(orderRepository.findById(1)).willReturn(Optional.of(o1));
		o1.setCost(7700);
		o1.setStatus("Dispatched");
		Order updatedOrder = orderService.updateOrder(1, o1);
		assertEquals("Dispatched",updatedOrder.getStatus());
		assertEquals(7700,updatedOrder.getCost());
		assertThrows(OrderNotFoundException.class, ()->{
			orderService.updateOrder(4,o1);
		});
	}
	
	@Test
	void testUpdateOrderStatus() throws OrderNotFoundException{
		given(orderRepository.findById(1)).willReturn(Optional.of(o1));
		Order updatedOrder = orderService.updateOrderStatus(1, "Dispatched");
		assertEquals("Dispatched",updatedOrder.getStatus());
		assertThrows(OrderNotFoundException.class, ()->{
			orderService.updateOrderStatus(4, "Dispatched");
		});
	}
	
	@Test
	void testUpdateOrderDispatchDate() throws OrderNotFoundException{
		given(orderRepository.findById(1)).willReturn(Optional.of(o1));
		Order updatedOrder = orderService.updateDispatchDate(1, LocalDate.of(2022, 8, 21));
		assertEquals(LocalDate.of(2022, 8, 21),updatedOrder.getDispatchDate());	
		assertThrows(OrderNotFoundException.class, ()->{
			orderService.updateDispatchDate(4, LocalDate.of(2022, 8, 21));
		});
	}
	
	@Test
	void testCancelOrder() throws OrderNotFoundException{
		given(orderRepository.findById(1)).willReturn(Optional.of(o1));
		Order updatedOrder = orderService.cancelOrder(1);
		assertEquals("Cancelled",updatedOrder.getStatus());	
		assertThrows(OrderNotFoundException.class, ()->{
			orderService.cancelOrder(4);
		});
	}
	
	@Test
	void testShowAllMobiles() throws OrderNotFoundException{
		given(orderRepository.findById(1)).willReturn(Optional.of(o1));
		Mobile m = orderService.showMobile(1);
		assertEquals(7800.0f,m.getMobileCost());	
		assertThrows(OrderNotFoundException.class, ()->{
			orderService.showMobile(4);
		});
	}
	
	@Test
	void testCalculateTotalCost() {
		int tc=orderService.calculateTotalCost(2800);
		assertEquals(2900,tc);
	}
	
	@Test
	void testShowOrder() throws OrderNotFoundException{
		given(orderRepository.findById(1)).willReturn(Optional.of(o1));
		Order order = orderService.showOrder(1);
		assertEquals("Ordered",order.getStatus());
		assertEquals(7800,order.getCost());	
		assertEquals(LocalDate.of(2022, 8, 9),order.getOrderDate());
		assertEquals(LocalDate.of(2022, 8, 10),order.getDispatchDate());
		assertEquals("aravind",order.getCustomer().getCustomerName());
		assertThrows(OrderNotFoundException.class, ()->{
			orderService.showOrder(4);
		});
	}
	
	@Test
	void testShowOrderByCustomer() throws CustomerNotFoundException{
		given(customerRepository.findById(1)).willReturn(Optional.of(cust1));
		given(orderRepository.findByCustomer(1)).willReturn(List.of(o1));
		List<Order> order = orderService.showOrderByCustomer(1);
		assertEquals("Ordered",order.get(0).getStatus());
		assertEquals(7800,order.get(0).getCost());	
		assertEquals(LocalDate.of(2022, 8, 9),order.get(0).getOrderDate());
		assertEquals(LocalDate.of(2022, 8, 10),order.get(0).getDispatchDate());
		assertEquals("aravind",order.get(0).getCustomer().getCustomerName());
		assertThrows(CustomerNotFoundException.class, ()->{
			orderService.showOrderByCustomer(4);
		});
	}
	
	@Test
	void testShowAllOrders() {
		when(orderRepository.findAll()).thenReturn(List.of(o1));
		List<Order> order= orderService.showAllOrders();
		assertNotNull(order);
		assertEquals(1,order.size());
		assertEquals("Ordered",order.get(0).getStatus());
		assertEquals(7800,order.get(0).getCost());	
		assertEquals(LocalDate.of(2022, 8, 9),order.get(0).getOrderDate());
		assertEquals(LocalDate.of(2022, 8, 10),order.get(0).getDispatchDate());
		assertEquals("aravind",order.get(0).getCustomer().getCustomerName());
	}

}
