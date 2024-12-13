package com.InventoryManagementSystem.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data

public class CategoryDto {


 private Long categoryId;


    @NotBlank(message = "categoryName name cannot be blank")
    @Size(max = 200, message = "categoryName name should not exceed 200 characters")
    private String categoryName;


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



}
