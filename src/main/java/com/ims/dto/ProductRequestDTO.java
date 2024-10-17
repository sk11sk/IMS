package com.ims.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Getter
@Setter
public class ProductRequestDTO {


    @Positive(message = "  vendorId must be greater than 0")
    Long vendorId;

    @NotBlank(message = "Vendor name cannot be blank")
    @Size(max = 50, message = "Vendor name should not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Vendor name should not contain any special characters or numbers")
    String vendorName;




    @Valid  // This is crucial to trigger validation on the nested DT
    CategoryDto category;

    @Valid  // This is crucial to trigger validation on the nested DT
    List<ProductDTO> products  = new ArrayList<>();

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

    
    
    
    
    
    
    
    

}
