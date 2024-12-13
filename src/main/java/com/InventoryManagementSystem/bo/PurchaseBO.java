package com.InventoryManagementSystem.bo;

import com.InventoryManagementSystem.dao.PurchaseRepository;
import com.InventoryManagementSystem.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseBO {



    @Autowired
    PurchaseRepository purchaseRepository;

    public Purchase getPurchaseDetailById(Long purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new ResourceNotFoundException("purchase with the given id not found "));
       return purchase;

    }

    public List<Purchase> getAllPurchaseDetail() {

        List<Purchase> all = purchaseRepository.findAll();

        return all;
    }


}
