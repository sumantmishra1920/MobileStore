package com.mycompany.onlinemobilestore;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

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
import com.mycompany.oms.exceptions.NoSuchCategoryException;
import com.mycompany.oms.exceptions.RecordExistsException;
import com.mycompany.oms.exceptions.UserNotFoundException;
import com.mycompany.oms.repository.ICategoryRepository;
import com.mycompany.oms.service.impl.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@Mock
	private ICategoryRepository repo;
	
	@InjectMocks
	private CategoryServiceImpl categoryService;
	
	
	private Category c1;
	
	@BeforeEach
	void setUp() {
		repo=Mockito.mock(ICategoryRepository.class);
		
		categoryService=new CategoryServiceImpl(repo);
		
		c1=new Category(1,"Basic Phone");	
	}
	
	@Test
	void testAddCategory() {
		when(repo.save(c1)).thenReturn(c1);
		Category savedCategory=categoryService.addCategory(c1);
		
		assertNotNull(savedCategory);
	}
	
	@Test
	void testUpdateCategory() throws NoSuchCategoryException  {
		given(repo.findById(1)).willReturn(Optional.of(c1));
		Category updatedCategory = categoryService.updateCategory(1, "Base Phone");
		
		assertEquals("Base Phone",updatedCategory.getCategoryName());
		
		assertThrows(NoSuchCategoryException.class, ()->{
			categoryService.updateCategory(9, "Base Phone");
		});
	}
	
	@Test
	void testDeleteCategory() throws NoSuchCategoryException {
		given(repo.findById(1)).willReturn(Optional.of(c1));
		Category deletedCategory=categoryService.deleteCategory(1);
		
		assertNotNull(deletedCategory);
		
		assertThrows(NoSuchCategoryException.class, ()->{
			categoryService.deleteCategory(9);
		});
	}
	
	@Test
	void testShowAllCategory() {
		Category c2=new Category(2,"Feature Phone");
		Category c3=new Category(3,"Smart Phone");
		when(repo.findAll()).thenReturn(List.of(c1,c2,c3));
		List<Category> cList= categoryService.showAllCategory();
		
		assertNotNull(cList);
		assertEquals(3,cList.size());
	}
	
	
}
