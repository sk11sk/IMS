package com.InventoryManagementSystem.dao;

import com.InventoryManagementSystem.entity.ProductTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTransactionLogRepository extends JpaRepository<ProductTransactionLog,Long> {
}
