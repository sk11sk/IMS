package com.ims.dto;

import java.util.Date;

public class ProductTransactionLogDTO {

    private Long transactionId;    // Corresponds to transaction_id
    private Long productId;         // Corresponds to product_id
    private Date createdAt;         // Corresponds to created_at
    private Long quantityChanged;      // Corresponds to quantity_changed
    private String transactionType;   // Corresponds to transaction_type
    private String barcode;           // Corresponds to barcode
    private String brand;             // Corresponds to brand
    private Double pricePerUnit;      // Corresponds to price_per_unit
    private String productName;       // Corresponds to product_name
    private Long quantity;            // Corresponds to quantity
    private Date updatedAt;         // Corresponds to updated_at
    private Long categoryId;        // Corresponds to category_id


    public ProductTransactionLogDTO(Long transactionId, Long productId, Date createdAt, Long quantityChanged, String transactionType, String barcode, String brand, Double pricePerUnit, String productName, Long quantity, Date updatedAt, Long categoryId) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.createdAt = createdAt;
        this.quantityChanged = quantityChanged;
        this.transactionType = transactionType;
        this.barcode = barcode;
        this.brand = brand;
        this.pricePerUnit = pricePerUnit;
        this.productName = productName;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
        this.categoryId = categoryId;
    }


    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getQuantityChanged() {
        return quantityChanged;
    }

    public void setQuantityChanged(Long quantityChanged) {
        this.quantityChanged = quantityChanged;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
