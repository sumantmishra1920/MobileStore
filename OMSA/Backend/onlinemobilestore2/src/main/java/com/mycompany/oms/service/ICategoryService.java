package com.mycompany.oms.service;

import java.util.List;


import com.mycompany.oms.entities.Category;
import com.mycompany.oms.exceptions.NoSuchCategoryException;

//Category service
public interface ICategoryService {
	
	public Category addCategory(Category category);
	
	public Category updateCategory(int id, String name) throws NoSuchCategoryException;
	
	public Category deleteCategory(int id) throws NoSuchCategoryException;
	
	public List<Category> showAllCategory();

}
