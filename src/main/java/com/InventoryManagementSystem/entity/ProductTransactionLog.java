package com.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Entity
@EntityListeners(AuditingEntityListener.class)

public class ProductTransactionLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    Long transactionId ;


    Long vendorOrCustomerId;


  long quantityChanged;

    public Long getVendorOrCustomerId() {
        return vendorOrCustomerId;
    }

    public void setVendorOrCustomerId(Long vendorOrCustomerId) {
        this.vendorOrCustomerId = vendorOrCustomerId;
    }

    String transactionType;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
  Date createdAt;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name  = "product_id" , referencedColumnName = "product_id" )
    private Product product;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public long getQuantityChanged() {
        return quantityChanged;
    }

    public void setQuantityChanged(long quantityChanged) {
        this.quantityChanged = quantityChanged;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
