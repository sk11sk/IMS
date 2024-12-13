package com.InventoryManagementSystem.controller;


import com.InventoryManagementSystem.dto.PurchaseResponseDTO;
import com.InventoryManagementSystem.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    // http://localhost:8080/api/purchase/getPurchaseDetailById/1
    @GetMapping("/getPurchaseDetailById/{purchaseId}")
    public ResponseEntity<PurchaseResponseDTO> getPurchaseDetailById(@PathVariable Long purchaseId){

        PurchaseResponseDTO purchaseResponseDTO =  purchaseService.getPurchaseDetailById(purchaseId);

        return new ResponseEntity<>(purchaseResponseDTO, HttpStatus.OK);
    }




    // http://localhost:8080/api/purchase/getAllPurchaseDetail
    @GetMapping("/getAllPurchaseDetail")
    public ResponseEntity< List<PurchaseResponseDTO>> getAllPurchaseDetail(){

        List<PurchaseResponseDTO> purchaseResponseDTOs =  purchaseService.getAllPurchaseDetail();

        return new ResponseEntity<>(purchaseResponseDTOs, HttpStatus.OK);
    }



}
