package com.InventoryManagementSystem.dao;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ProductCustomised {   // projection interface  for product entity


	public String getProductName(); // // Should match price alias in query and data type
	
	public Integer  getPrice();  // Should match price alias in query and data type
	
	
	public String getVendorName();
	
	public String getCategory();


	String getBrand();

	Long getProductId();


	Long getQuantity();





	// p.productName, v.vendorName
}
