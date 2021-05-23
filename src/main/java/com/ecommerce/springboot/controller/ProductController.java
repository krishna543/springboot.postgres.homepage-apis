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

import com.ecommerce.springboot.entities.Products;
import com.ecommerce.springboot.entities.SubCategory;
import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.repository.ProductsRepository;
import com.ecommerce.springboot.repository.SubCategoryRepository;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {
	
	@Autowired
	private ProductsRepository productsRepository;
	
	
	
	@Autowired
	private SubCategoryRepository subcategoryRepository;

	
	//get all Products
	@GetMapping("products")
	public List<Products> getAllProducts() {
		return this.productsRepository.findAll();
	}
	
	//get all Products by SubCategory
	@GetMapping("subcategory/{subcategoryId}/products")
	public List<Products> getAllProductsbySubCategory(@PathVariable(value = "subcategoryId") Long subcategoryId) {
		return this.productsRepository.findBysubCategoryId(subcategoryId);
	}
	

	//save SubCategory
	@PostMapping("subcategory/{subcategoryId}/products")
	public Products createProduct(@PathVariable(value = "subcategoryId") Long subcategoryId ,@RequestBody Products product)
				throws ResourceNotFoundException {
			
			return subcategoryRepository.findById(subcategoryId).map(subcategory -> {
				product.setSubCategory(subcategory);
				
				return productsRepository.save(product);
			}).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
			
	}
	
	//get Product by Id
	@GetMapping("subcategory/{subcategoryId}/products/{productId}")
	public Optional<Products> getproductById(@PathVariable(value = "subcategoryId") Long subcategoryId,@PathVariable(value = "productId") Long productId)
		
		throws ResourceNotFoundException{
			
			if (!subcategoryRepository.existsById(subcategoryId)&&productsRepository.existsById(productId)) {
	            throw new ResourceNotFoundException("categoryId::"+subcategoryId+"not found, but SubCategoryId::"+productId+"is found");
	        }
			
			else if (!productsRepository.existsById(productId)&&subcategoryRepository.existsById(subcategoryId)) {
	            throw new ResourceNotFoundException("subCategoryId::"+productId+" not found, but categoryId::"+subcategoryId+"is found");
	        }
			
			else 
				return productsRepository.findByIdAndSubCategoryId(productId,subcategoryId);
					
		}
	
	//update Product by Id
	@PutMapping("subcategory/{subcategoryId}/products/{productId}")
	public Optional<Products> updateProductById(@PathVariable(value = "subcategoryId") Long subcategoryId,@PathVariable(value = "productId") Long productId,
			
			@Validated @RequestBody Products updateProduct )
	
			throws ResourceNotFoundException{
				
				if (!subcategoryRepository.existsById(subcategoryId)&&productsRepository.existsById(productId)) {
		            throw new ResourceNotFoundException("categoryId::"+subcategoryId+"not found, but SubCategoryId::"+productId+"is found");
		        }
				
				else if (!productsRepository.existsById(productId)&&subcategoryRepository.existsById(subcategoryId)) {
		            throw new ResourceNotFoundException("subCategoryId::"+productId+" not found, but categoryId::"+subcategoryId+"is found");
		        }
				
				else 	
		return Optional.ofNullable(productsRepository.findById(productId).map(product -> {
			product.setProductName(updateProduct.getProductName());
			return productsRepository.save(product);
		}).orElseThrow(() -> new ResourceNotFoundException("Product id not found")));
	}
	

}
