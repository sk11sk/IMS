package com.InventoryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
@Data

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "orders")
public class Orders {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private  Long orderId;




   Long orderedQuantity;



    Double orderAmount;





    @ManyToOne
    @JoinColumn(name  = "customer_id" , referencedColumnName = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name  = "product_id", referencedColumnName = "product_id" )
    private Product product;



    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(long orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
