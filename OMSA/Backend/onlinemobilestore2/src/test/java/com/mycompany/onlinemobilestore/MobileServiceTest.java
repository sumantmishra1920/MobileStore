package com.mycompany.onlinemobilestore;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mycompany.oms.entities.Category;
import com.mycompany.oms.entities.Mobile;
import com.mycompany.oms.exceptions.MobileNotFoundException;
import com.mycompany.oms.exceptions.NoSuchCategoryException;
import com.mycompany.oms.repository.ICategoryRepository;
import com.mycompany.oms.repository.IMobileRepository;
import com.mycompany.oms.service.impl.MobileServiceImpl;

@ExtendWith(MockitoExtension.class)
class MobileServiceTest {
	
	@Mock
	private IMobileRepository repo;
	
	@Mock
	private ICategoryRepository catRepo;
	
	@InjectMocks
	private MobileServiceImpl mobService;
	
	private Mobile m1,m2,m3,m4,m5,m6;
	
	@BeforeEach
	void setUp() {
		repo=Mockito.mock(IMobileRepository.class);
		catRepo=Mockito.mock(ICategoryRepository.class);
		
		mobService=new MobileServiceImpl(repo,catRepo);
		
		m1=new Mobile(1,"Redmi 4",7800.0f,LocalDate.of(2018, 9, 12),"1111111","Xiaomi",new Category(3,"Smart Phone"));
		m2=new Mobile(2,"Redmi 5",9000.0f,LocalDate.of(2019, 7, 12),"1222111","Xiaomi",new Category(3,"Smart Phone"));
		m3=new Mobile(3,"iPhone 9",53000.0f,LocalDate.of(2018, 9, 22),"1342111","Apple",new Category(3,"Smart Phone"));
		m4=new Mobile(4,"Redmi 5",7800.0f,LocalDate.of(2018, 10, 11),"1111111","Xiaomi",new Category(3,"Smart Phone"));
		m5=new Mobile(5,"iPhone 11",78000.0f,LocalDate.of(2022, 1, 14),"1991111","Applw",new Category(3,"Smart Phone"));
		m6=new Mobile(6,"Nokia 3310",1200.0f,LocalDate.of(2010, 9, 12),"1114451","Nokia",new Category(1,"Basic Phone"));
	}
	
	@Test
	void testAddMobile() {
		when(repo.save(m1)).thenReturn(m1);
		Mobile savedMobile=mobService.addMobile(m1);
		
		assertNotNull(savedMobile);
	}
	
	@Test
	void testShowAllMobiles() {
		Mobile m2=new Mobile(2,"Redmi 5",9000.0f,LocalDate.of(2019, 7, 12),"1222111","Xiaomi",new Category(3,"Smart Phone"));
		Mobile m3=new Mobile(3,"iPhone 9",53000.0f,LocalDate.of(2018, 9, 22),"1342111","Apple",new Category(3,"Smart Phone"));
		Mobile m4=new Mobile(4,"Redmi 4",7800.0f,LocalDate.of(2018, 10, 11),"1111111","Xiaomi",new Category(3,"Smart Phone"));
		Mobile m5=new Mobile(5,"iPhone 11",78000.0f,LocalDate.of(2022, 1, 14),"1991111","Applw",new Category(3,"Smart Phone"));
		Mobile m6=new Mobile(6,"Nokia 3310",1200.0f,LocalDate.of(2010, 9, 12),"1114451","Nokia",new Category(1,"Basic Phone"));
		
		when(repo.findAll()).thenReturn(List.of(m1,m2,m3,m4,m5,m6));
		List<Mobile> mList= mobService.showAllMobiles();
		
		assertNotNull(mList);
		assertEquals(6,mList.size());
		
	}
	
	@Test
	void testUpdateMobile() throws MobileNotFoundException{
		given(repo.findById(1)).willReturn(Optional.of(m1));
		m1.setCompanyName("Mi");
		m1.setMobileCost(8000.0f);
		Mobile updatedMobile = mobService.updateMobile(1, m1);
		assertEquals("Mi",updatedMobile.getCompanyName());
		
		assertEquals(8000.0f,updatedMobile.getMobileCost());
		
		assertThrows(MobileNotFoundException.class, ()->{
			mobService.updateMobile(8, m1);
		});
		
	}
	
	@Test
	void testDeleteMobile() throws MobileNotFoundException {
		given(repo.findById(1)).willReturn(Optional.of(m1));
		Mobile deletedMobile = mobService.deleteMobile(1);
		
		assertNotNull(deletedMobile);
		
		assertThrows(MobileNotFoundException.class, ()->{
			mobService.deleteMobile(8);
		});
	}
	
	@Test
	void testShowMobileById() throws MobileNotFoundException {
		given(repo.findById(1)).willReturn(Optional.of(m1));
		Mobile mob = mobService.showMobileById(1);
		
		assertEquals("Redmi 4", mob.getMobileName());
		assertEquals("Xiaomi", mob.getCompanyName());
		assertEquals(LocalDate.of(2018, 9, 12), mob.getMfd());
		assertEquals(3, mob.getCategory().getCategoryId());
		assertEquals(7800.0f, mob.getMobileCost());
		
		assertThrows(MobileNotFoundException.class, ()->{
			mobService.showMobileById(0);
		});
	}
	
	@Test
	void testShowMobileByName() {
		when(repo.findByMobileName("Redmi 5")).thenReturn(List.of(m2,m4));
		List<Mobile> mList= mobService.showMobilesByName("Redmi 5");
		
		assertNotNull(mList);
		assertEquals(2,mList.size());
		assertEquals("Redmi 5", mList.get(0).getMobileName());
		assertEquals("Xiaomi", mList.get(0).getCompanyName());
		assertEquals(LocalDate.of(2019, 7, 12), mList.get(0).getMfd());
		assertEquals(3, mList.get(0).getCategory().getCategoryId());
		assertEquals(9000.0f, mList.get(0).getMobileCost());
	} 
	
	@Test
	void testAvailableQuantity() {
		when(repo.countByMobileName("Redmi 5")).thenReturn((long) 2);
		long qty= mobService.availableQuantity("Redmi 5");
		
		assertEquals(2,qty);
	}
	
	@Test
	void testShowMobilesByPriceRange() {
		when(repo.findMobilesInPriceRange(2000.0f,60000.0f )).thenReturn(List.of(m1,m2,m3,m4));
		List<Mobile> mList= mobService.showMobilesByPriceRange(2000.0f,60000.0f);
		
		assertNotNull(mList);
		assertEquals(4, mList.size());
		assertEquals("Redmi 4", mList.get(0).getMobileName());
		assertEquals("Xiaomi", mList.get(0).getCompanyName());
		assertEquals(LocalDate.of(2018, 9, 12), mList.get(0).getMfd());
		assertEquals(3, mList.get(0).getCategory().getCategoryId());
		assertEquals(7800.0f, mList.get(0).getMobileCost());
	}
	
	@Test
	void testShowMobilesByCategory() throws NoSuchCategoryException {
		Category cat=new Category(1,"Basic Phone");
		when(repo.findMobilesInCategory(cat)).thenReturn(List.of(m6));
		given(catRepo.findById(1)).willReturn(Optional.of(cat));
		List<Mobile> mList= mobService.showMobilesByCategory(1);
		
		assertNotNull(mList);
		assertEquals(1, mList.size());
		
		assertThrows(NoSuchCategoryException.class, ()->{
			mobService.showMobilesByCategory(0);
		});
	}
	
	@Test
	void testShowMobilesByCompany() {
		
		when(repo.findAllMobilesFromCompany("Apple")).thenReturn(List.of(m3, m5));
		List<Mobile> mList= mobService.showMobilesByCompany("Apple");
		
		assertNotNull(mList);
		assertEquals(2, mList.size());
		
	}
}
