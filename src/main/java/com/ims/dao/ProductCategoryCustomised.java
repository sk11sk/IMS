package com.ims.dao;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ProductCategoryCustomised {

public String getProductName(); // // Should match price alias in query and data type
	
	//public int  getPrice();  // Should match price alias in query and data type
	
	
	public String getCategoryName();
	
	public Long getTotalProducts();
	
	public Long getProductAmount();
	
	
	
}
