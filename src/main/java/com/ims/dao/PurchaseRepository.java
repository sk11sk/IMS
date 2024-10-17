package com.ims.dao;


import com.ims.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

//    @Query("SELECT new com.ims.dto.AllPurchaseDeatils(" +
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
