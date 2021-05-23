package com.ecommerce.springboot.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.ecommerce.springboot.entities.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Long>{
	
	List<Products> findBysubCategoryId(Long subcategoryId);
	Optional<Products> findByIdAndSubCategoryId(Long id, Long subcategoryId);
	

}
