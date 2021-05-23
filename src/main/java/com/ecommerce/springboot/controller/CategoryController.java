package com.ecommerce.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springboot.entities.Category;
import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.repository.CategoryRepository;

@RestController
@RequestMapping("/api/v1/")
public class CategoryController {

	@Autowired	
	private CategoryRepository categoryRepository;

	
	
	//getCategory
	@GetMapping("category")
	public List<Category> getCategories() {
		return this.categoryRepository.findAll();
		
	}
		
	//Post Mapping
    @PostMapping("category")
    public Category createCategory( @RequestBody Category category) {
        return categoryRepository.save(category);
    }
    
  //getCategorybyId
  	@GetMapping("/category/{id}")
  	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categorykey) throws ResourceNotFoundException {
  		
  		Category category  = categoryRepository.findById(categorykey)
  				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id:"+categorykey));
  				
  				return ResponseEntity.ok(category);
  	}
  	
  	//Update Category by Id
  	@PutMapping("category/{id}")
  	public ResponseEntity<Category> updateCategorybyId(@PathVariable(value ="id") Long Id,@Validated @RequestBody Category categorydata) throws ResourceNotFoundException{
  		Category category = categoryRepository.findById(Id)
  				.orElseThrow(() -> new ResourceNotFoundException("Category not found at this id"+Id));
  		
  		if(categorydata.getCategoryName()!=null)
  		{
  			category.setCategoryName(categorydata.getCategoryName());
  		}
  		
  		category.setActiveFlag(categorydata.isActiveFlag());
  	
  		final Category updatedCategory = categoryRepository.save(category);
  		return ResponseEntity.ok(updatedCategory);
  	}
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
}
