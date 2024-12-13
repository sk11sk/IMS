package com.InventoryManagementSystem.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Data
@Getter
@Setter
@Entity
@Table(name = "vendor")
@EntityListeners(AuditingEntityListener.class)
public class Vendor {    // child  - like comment has foreign key as product id 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "vendor_id")
    private Long vendorId;

    @NotBlank
    @Column(name = "vendor_name")
    private String vendorName;

    
	@NotNull
	@Column(unique = true)
	String vendorPhone;

	

	@NotBlank
	@Column(unique = true)
	String vendorEmail;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	
	
	@JoinTable(name = "vendor_product",
    joinColumns = @JoinColumn(name = "vendor_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
	    @ManyToMany (fetch = FetchType.EAGER) // you can add  @jointable() here  
	    private List<Product> products = new ArrayList<>();



	@OneToMany(mappedBy = "vendor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Purchase> purchases = new ArrayList<>();  //child  one vendor could have many purchases so list of purchases



	public Long getVendorId() {
		return vendorId;
	}



	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}



	public String getVendorName() {
		return vendorName;
	}



	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}



	public String getVendorPhone() {
		return vendorPhone;
	}



	public void setVendorPhone(String vendorPhone) {
		this.vendorPhone = vendorPhone;
	}



	public String getVendorEmail() {
		return vendorEmail;
	}



	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
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



	public List<Product> getProducts() {
		return products;
	}



	public void setProducts(List<Product> products) {
		this.products = products;
	}



	public List<Purchase> getPurchases() {
		return purchases;
	}



	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}










}
