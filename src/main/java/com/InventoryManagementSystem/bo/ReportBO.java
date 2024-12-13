package com.InventoryManagementSystem.bo;


import com.InventoryManagementSystem.dao.DashboardRepository;
import com.InventoryManagementSystem.dao.ReportRepository;
import com.InventoryManagementSystem.dto.DashboardDataDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportBO {
    @Autowired
    private  DashboardRepository dashboardRepository;
    @Autowired
    ReportRepository reportRepository;

    public  List<Object[]> generateReportForProduct(String productName, String brand) {

      return  reportRepository.findProductCustomerVendorReport(productName,brand);

    }

    public List<Object[]> findProductTransactionLogsByProductNameAndBrand(String productName, String brand) {

      return  reportRepository.findProductTransactionLogsByProductNameAndBrand(productName,brand);
    }

    public DashboardDataDTO findImsStats() {

        DashboardDataDTO dashboardData = new DashboardDataDTO();
        dashboardData.setTotalCustomers(dashboardRepository.getTotalCustomers());
        dashboardData.setTotalVendors(dashboardRepository.getTotalVendors());
        dashboardData.setTotalProducts(dashboardRepository.getTotalProducts());
        dashboardData.setTotalProductValue(dashboardRepository.getTotalProductValue());
        dashboardData.setTotalCategories(dashboardRepository.getAllCategoryCount());
        dashboardData.setCategoryDistribution(dashboardRepository.getCategoryDistribution());

        return dashboardData;



    }
}


//{
//  "totalCustomers": 120,
//  "totalVendors": 50,
//  "totalProducts": 500,
//  "totalProductValue": 1500000,
//  "totalCategories": 10,
//  "categoryDistribution": [
//    { "name": "Books", "value": 120 },
//    { "name": "Electronics", "value": 200 },
//    { "name": "Clothing", "value": 80 },
//    { "name": "Furniture", "value": 50 },
//    { "name": "Toys", "value": 50 }
//  ]
//}