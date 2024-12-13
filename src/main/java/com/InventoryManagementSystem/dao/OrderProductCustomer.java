package com.InventoryManagementSystem.dao;

public interface OrderProductCustomer {
    String getCustomerName();

    String getProductName();
    String getBrand();
    Double getPricePerUnit();
    Integer getOrderedQuantity();
    Double getOrderAmount();
    String getVendorName();
}
