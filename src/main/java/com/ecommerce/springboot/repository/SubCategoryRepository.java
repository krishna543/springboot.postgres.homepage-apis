package com.ecommerce.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.ecommerce.springboot.entities.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	
	List<SubCategory> findBycategoryId(Long categoryId);
	Optional<SubCategory> findByIdAndCategoryId(Long id, Long categoryId);
	
}
