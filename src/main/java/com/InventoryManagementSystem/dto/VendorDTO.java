package com.InventoryManagementSystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {



    private Long vendorId;


    @NotBlank(message = "Vendor name cannot be blank")
    @Size(max = 50, message = "Vendor name should not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Vendor name should not contain any special characters or numbers")
    String vendorName;

    @NotBlank(message = "Vendor phone cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Vendor phone must be a valid 10-digit number")
    String vendorPhone;



    @NotBlank(message = "Vendor email cannot be blank")
    @Email(message = "Vendor email should be valid")
    String vendorEmail;



    private Date createdAt;


    private Date updatedAt;


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


	public String getVendorPhone() {
		return vendorPhone;
	}


	public void setVendorPhone(String vendorPhone) {
		this.vendorPhone = vendorPhone;
	}


	public String getVendorEmail() {
		return vendorEmail;
	}


	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



}
