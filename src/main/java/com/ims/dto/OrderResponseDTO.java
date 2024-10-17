package com.ims.dto;

import java.util.Date;

public class OrderResponseDTO {

  long orderedQuantity;

  double orderedAmount;

  Date createdAt;



    ProductResponseDTO product;

    CustomerResponseDTO  customer;

    public long getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(long orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public double getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(double orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ProductResponseDTO getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDTO product) {
        this.product = product;
    }

    public CustomerResponseDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponseDTO customer) {
        this.customer = customer;
    }
}
