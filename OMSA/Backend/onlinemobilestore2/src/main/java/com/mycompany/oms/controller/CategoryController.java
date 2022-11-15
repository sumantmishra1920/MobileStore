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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.oms.entities.Category;
import com.mycompany.oms.exceptions.NoSuchCategoryException;
import com.mycompany.oms.service.ICategoryService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	ICategoryService categoryService;
	
	//To show all categories
	//@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/showAll")
	public ResponseEntity<List<Category>> showAll(){
		return new ResponseEntity<>(categoryService.showAllCategory(), HttpStatus.OK);
	}
	
	//To add a new category
	//@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/add")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
	}
	
	//To update a category
	//@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestParam("name") String name) throws NoSuchCategoryException{
		return new ResponseEntity<>(categoryService.updateCategory(id, name), HttpStatus.OK);
	}
	
	//To remove a category
	//@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") int id) throws NoSuchCategoryException{
		return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
	}
}
