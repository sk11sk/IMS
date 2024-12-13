package com.InventoryManagementSystem.dao;


import com.InventoryManagementSystem.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

//    @Query("SELECT new com.InventoryManagementSystem.dto.AllPurchaseDeatils(" +
//            "p.purchaseId, p.purchasedQuantity, p.createdAt, " +
//            "v.vendorId, v.vendorName, v.vendorPhone, " +
//            "pr.productId, pr.productName, pr.brand, pr.quantity, pr.pricePerUnit, pr.barcode, " +
//            "c.categoryName) " +
//            "FROM Purchase p " +
//            "JOIN p.vendor v " +
//            "JOIN p.product pr " +
//            "JOIN pr.category c")
//    List<AllPurchaseDeatils> findAllPurchasesWithDetails();
}
