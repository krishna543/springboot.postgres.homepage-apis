package com.ecommerce.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.springboot.entities.SubCategory;
import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.repository.CategoryRepository;
import com.ecommerce.springboot.repository.SubCategoryRepository;

@RestController
@RequestMapping("/api/v1/")
public class SubCategoryController {
	
	@Autowired
	private SubCategoryRepository subcategoryRepository;
		
	@Autowired
	private CategoryRepository categoryRepository;
	
		
	//get all sub categories
	@GetMapping("category/{category}/subcategory")
	public List<SubCategory> getallsubCategory(@PathVariable(value = "category") Long category) {
		return this.subcategoryRepository.findBycategoryId(category);
	}
	
	//save SubCategory
	@PostMapping("category/{category}/subcategory")
	public SubCategory createSubCategory(@PathVariable(value = "category") Long category ,@RequestBody SubCategory subcategory)
			throws ResourceNotFoundException {
		
		return categoryRepository.findById(category).map(category1 -> {
			subcategory.setCategory(category1);
			
			return subcategoryRepository.save(subcategory);
		}).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		
		
		}
	
	//get SubCategory by Id
	@GetMapping("category/{category}/subcategory/{subcategory}")
	public Optional<SubCategory> getsubCategoryById(@PathVariable(value = "category") Long categoryId,@PathVariable(value = "subcategory") Long subCategoryId)
	
	throws ResourceNotFoundException{
		
		if (!categoryRepository.existsById(categoryId)&&subcategoryRepository.existsById(subCategoryId)) {
            throw new ResourceNotFoundException("categoryId::"+categoryId+"not found, but SubCategoryId::"+subCategoryId+"is found");
        }
		
		else if (!subcategoryRepository.existsById(subCategoryId)&&categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("subCategoryId::"+subCategoryId+" not found, but categoryId::"+categoryId+"is found");
        }
		
		else 
			return subcategoryRepository.findByIdAndCategoryId(subCategoryId,categoryId);
				
	}
	
	//update SubCategory by Id
	@PutMapping("category/{category}/subcategory/{subcategory}")
	public Optional<SubCategory> updatesubCategoryById(@PathVariable(value = "category") Long categoryId,@PathVariable(value = "subcategory") Long subCategoryId,
			@Validated @RequestBody SubCategory updateSubCategory )
	
	throws ResourceNotFoundException{
		if (!categoryRepository.existsById(categoryId)&&subcategoryRepository.existsById(subCategoryId)) {
            throw new ResourceNotFoundException("categoryId::"+categoryId+"not found, but SubCategoryId::"+subCategoryId+"is found");
        }
		
		else if (!subcategoryRepository.existsById(subCategoryId)&&categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("subCategoryId::"+subCategoryId+" not found, but categoryId::"+categoryId+"is found");
        }
		
		else 
		return Optional.ofNullable(subcategoryRepository.findById(subCategoryId).map(subcategory -> {
			subcategory.setSubCategoryName(updateSubCategory.getSubCategoryName());
			return subcategoryRepository.save(subcategory);
		}).orElseThrow(() -> new ResourceNotFoundException("course id not found")));
	}
	
	
}
