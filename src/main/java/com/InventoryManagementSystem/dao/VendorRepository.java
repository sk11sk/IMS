package com.InventoryManagementSystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InventoryManagementSystem.entity.Vendor;
@Repository
public interface VendorRepository extends  JpaRepository<Vendor, Long>  {
    Optional<Vendor> findByVendorIdAndVendorName(Long vendorId, String vendorName);
}
