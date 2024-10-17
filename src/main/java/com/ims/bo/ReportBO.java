package com.ims.bo;


import com.ims.dao.ReportRepository;
import com.ims.dto.ProductCustomerVendorReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportBO {


    @Autowired
    ReportRepository reportRepository;

    public  List<Object[]> generateReportForProduct(String productName, String brand) {

      return  reportRepository.findProductCustomerVendorReport(productName,brand);

    }

    public List<Object[]> findProductTransactionLogsByProductNameAndBrand(String productName, String brand) {

      return  reportRepository.findProductTransactionLogsByProductNameAndBrand(productName,brand);
    }
}
