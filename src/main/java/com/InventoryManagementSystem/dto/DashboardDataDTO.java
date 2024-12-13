package com.InventoryManagementSystem.dto;

import java.util.List;

public class DashboardDataDTO {

    private int totalCustomers;
    private int totalVendors;
    private int totalProducts;
    private long totalProductValue;
    private int totalCategories;
    private List<CategoryDistributionDTO> categoryDistribution;

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public int getTotalVendors() {
        return totalVendors;
    }

    public void setTotalVendors(int totalVendors) {
        this.totalVendors = totalVendors;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalProductValue() {
        return totalProductValue;
    }

    public void setTotalProductValue(long totalProductValue) {
        this.totalProductValue = totalProductValue;
    }

    public int getTotalCategories() {
        return totalCategories;
    }

    public void setTotalCategories(int totalCategories) {
        this.totalCategories = totalCategories;
    }

    public List<CategoryDistributionDTO> getCategoryDistribution() {
        return categoryDistribution;
    }

    public void setCategoryDistribution(List<CategoryDistributionDTO> categoryDistribution) {
        this.categoryDistribution = categoryDistribution;
    }
// Getters and setters
}
