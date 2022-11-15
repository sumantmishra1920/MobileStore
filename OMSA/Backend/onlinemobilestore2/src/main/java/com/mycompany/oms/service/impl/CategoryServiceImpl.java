package com.mycompany.oms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.oms.entities.Category;
import com.mycompany.oms.exceptions.NoSuchCategoryException;
import com.mycompany.oms.repository.ICategoryRepository;
import com.mycompany.oms.service.ICategoryService;

//Category service implementation
@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryRepository categoryRepository;
	
	public CategoryServiceImpl(ICategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	//To add a new category
	@Override
	public Category addCategory(Category category) {
		
		return categoryRepository.save(category);
	}

	//To update a category
	@Override
	public Category updateCategory(int id, String name) throws NoSuchCategoryException {
		Optional<Category> opt = categoryRepository.findById(id);
		if(opt.isPresent()) {
			Category existingCategory = opt.get();
			existingCategory.setCategoryName(name);
			categoryRepository.save(existingCategory);
			return existingCategory;
		} else {
			throw new NoSuchCategoryException("No category with id "+id);
		}
	}

	//To remove a category
	@Override
	public Category deleteCategory(int id) throws NoSuchCategoryException {
		Optional<Category> opt = categoryRepository.findById(id);
		if(opt.isPresent()){
			Category deletedCategory=opt.get();
			categoryRepository.deleteById(id);
			return deletedCategory;
		} else {
			throw new NoSuchCategoryException("No category with id "+id);
		}
	}

	//To show all categories
	@Override
	public List<Category> showAllCategory() {
		return categoryRepository.findAll();
	}
	
}
