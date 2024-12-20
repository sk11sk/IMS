package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.bo.VendorBO;
import com.InventoryManagementSystem.dto.VendorDTO;
import com.InventoryManagementSystem.entity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class VendorService {



    @Autowired
    VendorBO  vendorBO;
    public VendorDTO addVendor(VendorDTO vendorDTO) {

        Vendor addedVendor = vendorBO.addVendor(vendorDTO);

        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setVendorId(addedVendor.getVendorId());
        vendorDTO1.setVendorName(addedVendor.getVendorName());
        vendorDTO1.setVendorPhone(addedVendor.getVendorPhone());
        vendorDTO1.setVendorEmail(addedVendor.getVendorEmail());
        vendorDTO1.setCreatedAt(addedVendor.getCreatedAt());
        vendorDTO1.setUpdatedAt(addedVendor.getUpdatedAt());
        return vendorDTO1;
    }

    public VendorDTO getVendorById(Long vendorId) {

        Vendor addedVendor = vendorBO.getVendorById(vendorId);
        VendorDTO vendorDTO1 = new VendorDTO();

        vendorDTO1.setVendorId(addedVendor.getVendorId());
        vendorDTO1.setVendorName(addedVendor.getVendorName());
        vendorDTO1.setVendorPhone(addedVendor.getVendorPhone());
        vendorDTO1.setVendorEmail(addedVendor.getVendorEmail());
        vendorDTO1.setCreatedAt(addedVendor.getCreatedAt());
        vendorDTO1.setUpdatedAt(addedVendor.getUpdatedAt());
        return vendorDTO1;
    }

    public List<VendorDTO> getAllVendors() {

        List<Vendor> allVendors = vendorBO.getAllVendors();

        List<VendorDTO> vendorDTOS = new ArrayList<>();

        for (Vendor addedVendor: allVendors) {

            VendorDTO vendorDTO1 = new VendorDTO();
            vendorDTO1.setVendorId(addedVendor.getVendorId());
            vendorDTO1.setVendorName(addedVendor.getVendorName());
            vendorDTO1.setVendorPhone(addedVendor.getVendorPhone());
            vendorDTO1.setVendorEmail(addedVendor.getVendorEmail());
            vendorDTO1.setCreatedAt(addedVendor.getCreatedAt());
            vendorDTO1.setUpdatedAt(addedVendor.getUpdatedAt());
            vendorDTOS.add(vendorDTO1);
        }

return vendorDTOS;
    }


    public void  addVendorList(List<VendorDTO> vendorDTOs) {

          vendorBO.addVendorList(vendorDTOs);




    }

    public VendorDTO getVendorNameByVendorById(Long vendorId) {

        Vendor addedVendor = vendorBO.getVendorById(vendorId);
        VendorDTO vendorDTO1 = new VendorDTO();
        vendorDTO1.setVendorName(addedVendor.getVendorName());
        return vendorDTO1;
    }

    public List<VendorDTO> getAllVendorsId() {


        List<Vendor> allVendors = vendorBO.getAllVendors();

        List<VendorDTO> vendorDTOS = new ArrayList<>();

        for (Vendor addedVendor: allVendors) {

            VendorDTO vendorDTO1 = new VendorDTO();
            vendorDTO1.setVendorId(addedVendor.getVendorId());

            vendorDTOS.add(vendorDTO1);
        }

        return vendorDTOS;
    }
}
