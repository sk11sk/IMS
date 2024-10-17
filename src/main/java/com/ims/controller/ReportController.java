package com.ims.controller;

import com.ims.dto.ProductCustomerVendorReportDTO;
import com.ims.dto.ProductTransactionLogDTO;
import com.ims.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {


    @Autowired
    private ReportService reportService;


    //http://localhost:8080/api/reports/product/{productName}/brand/{brandName}
    @GetMapping("/product/{productName}/brand/{brand}")
    public List<ProductCustomerVendorReportDTO> getReportByProductAndBrand(
            @PathVariable String productName,
            @PathVariable String brand) {
     return  reportService.generateReportForProduct(productName, brand);



    }


    //http://localhost:8080/api/reports/findProductTransactionLogsByProductNameAndBrand/product/{productName}/brand/{brandName}
    @GetMapping("/findProductTransactionLogsByProductNameAndBrand/product/{productName}/brand/{brand}")
    public List<ProductTransactionLogDTO> findProductTransactionLogsByProductNameAndBrand(
            @PathVariable String productName,
            @PathVariable String brand) {
        return  reportService.findProductTransactionLogsByProductNameAndBrand(productName, brand);



    }

}
