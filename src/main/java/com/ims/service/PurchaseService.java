package com.ims.service;


import com.ims.bo.PurchaseBO;
import com.ims.dto.*;
import com.ims.entity.Category;
import com.ims.entity.Product;
import com.ims.entity.Purchase;
import com.ims.entity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseService {


    @Autowired
    PurchaseBO purchaseBO;
    public PurchaseResponseDTO getPurchaseDetailById(Long purchaseId) {

       Purchase purchase =  purchaseBO.getPurchaseDetailById(purchaseId);


        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();

        // set purchase details
        PurchaseDTO purchaseDTO = new PurchaseDTO();

        purchaseDTO.setPurchaseId(purchase.getPurchaseId());
        purchaseDTO.setPurchasedQuantity(purchase.getPurchasedQuantity());

        purchaseDTO.setCreatedAt(purchase.getCreatedAt());

        purchaseResponseDTO.setPurchase(purchaseDTO);


        // set product details


        ProductDTO productDTO = new ProductDTO();
        Product product = purchase.getProduct();

        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setBrand(product.getBrand());
        productDTO.setBarcode(product.getBarcode());

        productDTO.setQuantity(product.getQuantity());
        productDTO.setPricePerUnit(product.getPricePerUnit());

        purchaseResponseDTO.setProduct(productDTO);
        // set  category of the associated product


        Category category = product.getCategory();

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(category.getCategoryName());

        purchaseResponseDTO.setCategory(categoryDto);


        // set associated vendor

        Vendor vendor = purchase.getVendor();


        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setVendorId(vendor.getCustomerId());
        vendorDTO.setVendorName(vendor.getVendorName());
        vendorDTO.setVendorPhone(vendor.getVendorPhone());


        purchaseResponseDTO.setVendor(vendorDTO);



        return  purchaseResponseDTO;
    }
    // if you ever want to find list of something  go with by id first then  just add List<>  and for loop thats it

 public List<PurchaseResponseDTO> getAllPurchaseDetail() {

 List<Purchase>  purchases =purchaseBO.getAllPurchaseDetail();

  List<PurchaseResponseDTO> purchaseResponseDTOS = new ArrayList<>();

  for (Purchase purchase:purchases){


   PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();

   // set purchase details
   PurchaseDTO purchaseDTO = new PurchaseDTO();

   purchaseDTO.setPurchaseId(purchase.getPurchaseId());
   purchaseDTO.setPurchasedQuantity(purchase.getPurchasedQuantity());

   purchaseDTO.setCreatedAt(purchase.getCreatedAt());

   purchaseResponseDTO.setPurchase(purchaseDTO);


   // set product details


   ProductDTO productDTO = new ProductDTO();
   Product product = purchase.getProduct();

   productDTO.setProductId(product.getProductId());
   productDTO.setProductName(product.getProductName());
   productDTO.setBrand(product.getBrand());
   productDTO.setBarcode(product.getBarcode());
   productDTO.setQuantity(product.getQuantity());
   productDTO.setPricePerUnit(product.getPricePerUnit());

   purchaseResponseDTO.setProduct(productDTO);
   // set  category of the associated product


   Category category = product.getCategory();

   CategoryDto categoryDto = new CategoryDto();
   categoryDto.setCategoryName(category.getCategoryName());

   purchaseResponseDTO.setCategory(categoryDto);


   // set associated vendor

   Vendor vendor = purchase.getVendor();


   VendorDTO vendorDTO = new VendorDTO();
   vendorDTO.setVendorId(vendor.getCustomerId());
   vendorDTO.setVendorName(vendor.getVendorName());
   vendorDTO.setVendorPhone(vendor.getVendorPhone());


   purchaseResponseDTO.setVendor(vendorDTO);

   purchaseResponseDTOS.add(purchaseResponseDTO);
  }


 return purchaseResponseDTOS;
 }

}
