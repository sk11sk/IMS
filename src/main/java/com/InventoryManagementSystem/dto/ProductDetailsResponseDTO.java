package com.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Getter
@Setter
public class ProductDetailsResponseDTO {


    ProductDTO product;

    CategoryDto category;

    List<VendorDTO> vendors = new ArrayList<>();

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public List<VendorDTO> getVendors() {
		return vendors;
	}

	public void setVendors(List<VendorDTO> vendors) {
		this.vendors = vendors;
	}
    
    
    
    
    
    


}
