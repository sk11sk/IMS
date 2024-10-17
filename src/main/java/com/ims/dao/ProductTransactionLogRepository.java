package com.ims.dao;

import com.ims.entity.ProductTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTransactionLogRepository extends JpaRepository<ProductTransactionLog,Long> {
}
