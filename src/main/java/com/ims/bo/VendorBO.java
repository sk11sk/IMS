package com.ims.bo;

import java.util.List;
import java.util.Optional;

import com.ims.dto.VendorDTO;
import com.ims.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ims.dao.ProductCustomised;
import com.ims.dao.VendorRepository;
import com.ims.entity.Vendor;


@Component
public class VendorBO {

	@Autowired
	private VendorRepository vendorRepository;
	

    public Vendor addVendor(VendorDTO vendorDTO) {
		Vendor vendor= new Vendor();
		vendor.setVendorName(vendorDTO.getVendorName());
		vendor.setVendorPhone(vendorDTO.getVendorPhone());
		vendor.setVendorEmail(vendorDTO.getVendorEmail());

		Vendor savedVendor = vendorRepository.save(vendor);
return  savedVendor;

	}


	public Vendor getVendorById(Long vendorId) {

		Vendor savedVendor =	vendorRepository.findById(vendorId).orElseThrow(()-> new ResourceNotFoundException("vendor with the id  not found"));
		return savedVendor;
	}


	public List<Vendor> getAllVendors() {

		List<Vendor> vendors = vendorRepository.findAll();
		return vendors;
	}


	public void addVendorList(List<VendorDTO> vendorDTOs) {

		 for (VendorDTO vendorDTO:vendorDTOs){
			 Vendor vendor= new Vendor();
			 vendor.setVendorName(vendorDTO.getVendorName());
			 vendor.setVendorPhone(vendorDTO.getVendorPhone());
			 vendor.setVendorEmail(vendorDTO.getVendorEmail());

			 Vendor savedVendor = vendorRepository.save(vendor);

		 }


	}
}
