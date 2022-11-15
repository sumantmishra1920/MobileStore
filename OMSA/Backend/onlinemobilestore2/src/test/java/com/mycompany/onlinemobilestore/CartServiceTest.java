package com.mycompany.onlinemobilestore;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.oms.entities.Cart;
import com.mycompany.oms.entities.Category;
import com.mycompany.oms.entities.Customer;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.entities.Order;
import com.mycompany.oms.exceptions.CartNotFoundException;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.exceptions.NoSuchCategoryException;
import com.mycompany.oms.repository.ICartRepository;
import com.mycompany.oms.repository.ICustomerRepository;
import com.mycompany.oms.repository.IMobileRepository;
import com.mycompany.oms.repository.IOrderRepository;
import com.mycompany.oms.service.impl.CartServiceImpl;
import com.mycompany.oms.service.impl.MobileServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
	
	@Mock
	private ICartRepository cartRepo;
	
	@Mock
	private IMobileRepository mobileRepo;
	
	@Mock
	private IOrderRepository orderRepo;
	
	private Cart c1;
	private Mobile m1,m2,m3,m4;
	private Customer cust1;
	
	@InjectMocks
	private CartServiceImpl cartService;
	
	
	@BeforeEach
	void setUp() {
		cartRepo=Mockito.mock(ICartRepository.class);
		orderRepo=Mockito.mock(IOrderRepository.class);
		mobileRepo=Mockito.mock(IMobileRepository.class);
		
		cartService=new CartServiceImpl(cartRepo, mobileRepo,orderRepo);
		
		m1=new Mobile(1,"Redmi 4",7800.0f,LocalDate.of(2018, 9, 12),"1111111","Xiaomi",new Category(3,"Smart Phone"));
		m2=new Mobile(2,"Redmi 5",9000.0f,LocalDate.of(2019, 7, 12),"1222111","Xiaomi",new Category(3,"Smart Phone"));
		m3=new Mobile(3,"iPhone 9",53000.0f,LocalDate.of(2018, 9, 22),"1342111","Apple",new Category(3,"Smart Phone"));
		m4=new Mobile(4,"Redmi 6",7800.0f,LocalDate.of(2018, 10, 11),"1111111","Xiaomi",new Category(3,"Smart Phone"));
	
		cust1=new Customer(1,"Sumant","sumant@gmail.com",7337784414L,"Bangalore");
		
		List<Mobile> mList=new ArrayList<Mobile>();
		mList.add(m1);
		mList.add(m2);
		mList.add(m3);
		
		c1=new Cart(1,cust1,mList,3,(7800+9000+53000));
	}
	
	@Test
	void testCreateCart() {
		when(cartRepo.save(ArgumentMatchers.<Cart>any())).thenReturn(c1);
		Cart savedCart=cartService.createCart(cust1);
		
		assertNotNull(savedCart);
		assertEquals("Sumant",savedCart.getCustomer().getCustomerName());
	}
	
	@Test
	void testShowAll() {
		when(cartRepo.findAll()).thenReturn(List.of(c1));
		List<Cart> cList= cartService.showAll();
		
		assertNotNull(cList);
		assertEquals(1,cList.size());
		assertEquals("Redmi 4",cList.get(0).getMobiles().get(0).getMobileName());
	}
	
	@Test 
	void testDeleteMobile() throws CartNotFoundException, MobileNotFoundException {
		when(cartRepo.findById(1)).thenReturn(Optional.of(c1));
		when(mobileRepo.findById(1)).thenReturn(Optional.of(m1));
		when(cartRepo.save(c1)).thenReturn(c1);
		Cart c=cartService.deleteMobile(1, 1);
		
		assertEquals(2,c.getMobiles().size());
		
		assertThrows(CartNotFoundException.class, ()->{
			cartService.deleteMobile(4,1);
		});
		
		assertThrows(MobileNotFoundException.class, ()->{
			cartService.deleteMobile(1,6);
		});
	}
	
	@Test
	void testDeleteMobiles() throws CartNotFoundException {
		when(cartRepo.findById(1)).thenReturn(Optional.of(c1));
		when(cartRepo.save(c1)).thenReturn(c1);
		Cart c=cartService.deleteMobiles(1);
		
		assertEquals(0,c.getMobiles().size());
		
		assertThrows(CartNotFoundException.class, ()->{
			cartService.deleteMobiles(6);
		});
	} 
	
	@Test
	void testAddToCart() throws MobileNotFoundException, CartNotFoundException {
		when(cartRepo.findById(1)).thenReturn(Optional.of(c1));
		when(mobileRepo.findById(4)).thenReturn(Optional.of(m4));
		when(cartRepo.findAll()).thenReturn(List.of(c1));
		when(orderRepo.findAll()).thenReturn(new ArrayList<Order>());
		when(cartRepo.save(c1)).thenReturn(c1);
		Cart savedCart=cartService.addToCart(1,4);
		
		assertEquals(4,savedCart.getMobiles().size());
		assertEquals("Redmi 6",c1.getMobiles().get(3).getMobileName());
		assertEquals("Xiaomi",c1.getMobiles().get(3).getCompanyName());
		assertEquals(7800.0f,c1.getMobiles().get(3).getMobileCost());
		
		assertThrows(CartNotFoundException.class, ()->{
			cartService.addToCart(6,4);
		});
		
		assertThrows(MobileNotFoundException.class, ()->{
			cartService.addToCart(1,8);
		});
	}
	
	@Test
	void  testViewMobiles() throws CartNotFoundException{
		given(cartRepo.findById(1)).willReturn(Optional.of(c1));
		List<Mobile> mList=cartService.viewMobiles(1);
		
		assertEquals(1,mList.get(0).getMobileId());
		assertEquals("Redmi 4",mList.get(0).getMobileName());
		assertEquals("Redmi 5",mList.get(1).getMobileName());
		assertEquals(53000.0f,mList.get(2).getMobileCost());
		
		assertThrows(CartNotFoundException.class, ()->{
			cartService.viewMobiles(6);
		});
	}
	
	@Test
	void testViewCartInfo() throws CartNotFoundException{
		
		given(cartRepo.findById(1)).willReturn(Optional.of(c1));
		Cart cart= cartService.viewCartInfo(1);
		
		assertEquals(3,cart.getQuantity());
		assertEquals("Redmi 4",c1.getMobiles().get(0).getMobileName());
		assertEquals("Redmi 5",c1.getMobiles().get(1).getMobileName());
		assertEquals(53000.0f,c1.getMobiles().get(2).getMobileCost());
		assertThrows(CartNotFoundException.class, ()->{
			cartService.viewCartInfo(6);
		});
	}
	
	@Test
	void testAddMobiles() throws MobileNotFoundException, CartNotFoundException{
		List<Mobile> mList=new ArrayList<Mobile>();
		mList.add(m4);
		given(mobileRepo.findByMobileName("Redmi 6")).willReturn(mList);
		when(cartRepo.findAll()).thenReturn(List.of(c1));
		given(orderRepo.findAll()).willReturn(List.of());
		given(mobileRepo.countByMobileName("Redmi 6")).willReturn(4L);
		given(cartRepo.findById(1)).willReturn(Optional.of(c1));
		Cart c=cartService.addMobiles(1,"Redmi 6",1);
		
		assertEquals(4,c.getMobiles().size());
		
		assertThrows(CartNotFoundException.class, ()->{
			cartService.addMobiles(6,"Redmi 6",1);
		});
		
		assertThrows(MobileNotFoundException.class, ()->{
			cartService.addMobiles(1,"Redmi 11",1);
		});
		
		assertThrows(MobileNotFoundException.class, ()->{
			cartService.addMobiles(1,"Redmi 6",6);
		});
	}
	
	@Test
	void testAddMobilesNotAvailable() throws MobileNotFoundException, CartNotFoundException{
		List<Mobile> mList=new ArrayList<Mobile>();
		mList.add(m4);
		given(mobileRepo.findByMobileName("iPhone 9")).willReturn(mList);
		when(cartRepo.findAll()).thenReturn(List.of(c1));
		given(orderRepo.findAll()).willReturn(List.of());
		given(mobileRepo.countByMobileName("iPhone 9")).willReturn(0L);
		
		assertThrows(MobileNotFoundException.class, ()->{
			cartService.addMobiles(1,"iPhone 9",1);
		});
	}
}
