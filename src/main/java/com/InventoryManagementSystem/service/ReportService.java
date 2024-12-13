package com.InventoryManagementSystem.service;


import com.InventoryManagementSystem.bo.ReportBO;
import com.InventoryManagementSystem.dto.DashboardDataDTO;

import com.InventoryManagementSystem.dto.ProductCustomerVendorReportDTO;
import com.InventoryManagementSystem.dto.ProductTransactionLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ReportService {

    @Autowired
   private ReportBO reportBO;
    public  List<ProductCustomerVendorReportDTO> generateReportForProduct(String productName, String brand) {

       reportBO.generateReportForProduct(productName,brand);


        List<Object[]> results = reportBO.generateReportForProduct(productName, brand);

        List<ProductCustomerVendorReportDTO> reportDTOs = new ArrayList<>();

        for (Object[] result : results) {
            ProductCustomerVendorReportDTO dto = new ProductCustomerVendorReportDTO(
                    (String) result[0],    // productName
                    (String) result[1],    // brand
                    (Long) result[2] , // orderedQuantity
                    (Long) result[3] ,  // purchasedQuantity
                    (String) result[4],    // customerName
                    (String) result[5]     // vendorName
            );
            reportDTOs.add(dto);
        }

        return reportDTOs;
        }

    public List<ProductTransactionLogDTO> findProductTransactionLogsByProductNameAndBrand(String productName, String brand) {

        List<Object[]>  objects =  reportBO.findProductTransactionLogsByProductNameAndBrand(productName,brand);

        List<ProductTransactionLogDTO> dtos = new ArrayList<>();

        for (Object[] result : objects) {

            ProductTransactionLogDTO dto = new ProductTransactionLogDTO(
                    (Long) result[0],  // transactionId
                    (Long) result[1],  // productId
                    (Date) result[2],  // created at
                    (Long) result[3],  // quantityChanged;
                    (String) result[4],  // transactionTpe
                    (String) result[5],  // barcode
                    (String) result[6],  // brand
                    (Double ) result[7],  // price per unit

                    (String) result[8],  // productName
                    (Long) result[9], // quantity
                     (Date) result[10], // updated at
                    (Long) result[11],  // category id
                     (String) result[12],  // vendorName
                    (String) result[13] // customerName






            );
            dtos.add(dto);
        }

        return dtos;

    }

    public DashboardDataDTO findImsStats() {

       return  reportBO.findImsStats();


    }
}




