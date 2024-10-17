package com.ims.dao;

import com.ims.entity.Customer;
import com.ims.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
    @Repository
    public interface CustomerRepository extends JpaRepository<Customer, Long> {
        Optional<Customer> findByCustomerPhoneAndCustomerName(String customerPhone, String customerName);
    }

