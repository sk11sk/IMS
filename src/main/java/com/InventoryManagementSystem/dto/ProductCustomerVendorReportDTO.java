package com.InventoryManagementSystem.dto;

public class ProductCustomerVendorReportDTO {



        private String productName;
        private String brand;
        private Long orderedQuantity;
        private Long purchasedQuantity;
        private String customerName;
        private String vendorName;

        // Getters and Setters
//   public  ProductCustomerVendorReportDTO(){
//
//    }

    public ProductCustomerVendorReportDTO(String productName, String brand,
                                          Long orderedQuantity, Long purchasedQuantity, String customerName, String vendorName) {
        this.productName = productName;
        this.brand = brand;
        this.orderedQuantity = orderedQuantity;
        this.purchasedQuantity = purchasedQuantity;
        this.customerName = customerName;
        this.vendorName = vendorName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Long orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Long getPurchasedQuantity() {
        return purchasedQuantity;
    }

    public void setPurchasedQuantity(Long purchasedQuantity) {
        this.purchasedQuantity = purchasedQuantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
