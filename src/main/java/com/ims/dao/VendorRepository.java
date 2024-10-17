package com.ims.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ims.entity.Vendor;
@Repository
public interface VendorRepository extends  JpaRepository<Vendor, Long>  {
    Optional<Vendor> findByVendorIdAndVendorName(Long vendorId, String vendorName);
}
