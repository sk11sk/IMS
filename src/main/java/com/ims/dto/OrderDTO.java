package com.ims.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDTO {


    Long orderId;


    long orderdQuantity;



    double orderAmount;


    Date createdAt;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getOrderdQuantity() {
        return orderdQuantity;
    }

    public void setOrderdQuantity(long orderdQuantity) {
        this.orderdQuantity = orderdQuantity;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
