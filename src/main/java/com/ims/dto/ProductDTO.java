package com.ims.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

   Long productId;


 @NotBlank(message = "brand  cannot be blank")
 @Size(max = 200, message = "brand should not exceed 200 characters")
    String brand;

 @NotBlank(message = "Product name cannot be blank")
 @Size(max = 200, message = "Product name should not exceed 200 characters")
    String productName;


	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
 @Positive(message = "Product quantity must be greater than 0")
 long quantity;


	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
 @Positive(message = "Product price must be greater than 0")
    double pricePerUnit;


 String  barcode;  // not an input

 private Date createdAt;// not an input


 private Date updatedAt;// not an input

	OrderDTO order;

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public Long getProductId() {
	return productId;
}


public void setProductId(Long productId) {
	this.productId = productId;
}


public String getBrand() {
	return brand;
}


public void setBrand(String brand) {
	this.brand = brand;
}


public String getProductName() {
	return productName;
}


public void setProductName(String productName) {
	this.productName = productName;
}


public long getQuantity() {
	return quantity;
}


public void setQuantity(long quantity) {
	this.quantity = quantity;
}


public double getPricePerUnit() {
	return pricePerUnit;
}


public void setPricePerUnit(double pricePerUnit) {
	this.pricePerUnit = pricePerUnit;
}


public String getBarcode() {
	return barcode;
}


public void setBarcode(String barcode) {
	this.barcode = barcode;
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
