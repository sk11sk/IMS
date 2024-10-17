package com.ims.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDTO {



    private Long customerId;


    @NotBlank(message = "customer name cannot be blank")
    @Size(max = 50, message = "customer name should not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "customer name should not contain any special characters or numbers")
    String customerName;

    @NotBlank(message = "customer  phone cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "customer phone must be a valid 10-digit number")
    String customerPhone;



    @NotBlank(message = "customer email cannot be blank")
    @Email(message = "customer email should be valid")
    String customerEmail;



    private Date createdAt;


    private Date updatedAt;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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
