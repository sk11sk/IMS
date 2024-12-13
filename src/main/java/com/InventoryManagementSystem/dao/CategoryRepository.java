package com.InventoryManagementSystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InventoryManagementSystem.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

	Optional<Category> findByCategoryName(String categoryName);
	
//	    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
//	    Category findCategoryByCategoryName(@Param("categoryName") String categoryName);
}
