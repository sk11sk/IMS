package com.InventoryManagementSystem.dao;

import com.InventoryManagementSystem.dto.CategoryDistributionDTO;
import com.InventoryManagementSystem.dto.DashboardDataDTO;
import com.InventoryManagementSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Product, Long> {

    @Query("SELECT COUNT(p.id) FROM Product p")
    int getTotalProducts();

    @Query("SELECT COUNT(c.id) FROM Customer c")
    int getTotalCustomers();

    @Query("SELECT COUNT(v.id) FROM Vendor v")
    int getTotalVendors();

    @Query("SELECT  COUNT(c.id) FROM Category c")
     int getAllCategoryCount();

    @Query("SELECT SUM(p.quantity * p.pricePerUnit) FROM Product p")
    Long getTotalProductValue();

    @Query("SELECT new com.InventoryManagementSystem.dto.CategoryDistributionDTO(c.categoryName, SUM(p.quantity)) " +
            "FROM Product p JOIN p.category c " +
            "GROUP BY c.categoryName")
    List<CategoryDistributionDTO> getCategoryDistribution();
}
//categoryName