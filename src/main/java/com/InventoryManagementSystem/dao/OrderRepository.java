package com.InventoryManagementSystem.dao;

import com.InventoryManagementSystem.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {


    @Query("SELECT c.customerName AS customerName, " +
            "p.productName AS productName, " +
            "p.brand AS brand, " +
            "p.pricePerUnit AS pricePerUnit, " +
            "o.orderedQuantity AS orderedQuantity, " +
            "o.orderAmount AS orderAmount, " +
            "v.vendorName AS vendorName " +
            "FROM Customer c " +
            "JOIN c.products p " +
            "JOIN c.orders o " +
            "JOIN p.vendors v " +
            "WHERE o.product = p")
    List<OrderProductCustomer> findOrderProductCustomerDetails();

}
