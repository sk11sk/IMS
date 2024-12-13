package com.InventoryManagementSystem.dto;

public class CategoryDistributionDTO {
    private String categoryName;
    private long stock;

    public CategoryDistributionDTO(String categoryName, long stock) {
        this.categoryName = categoryName;
        this.stock = stock;
    }

    // Getters and setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }
}
