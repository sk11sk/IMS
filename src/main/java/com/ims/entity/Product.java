package com.ims.entity;

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

@Data
@Getter
@Setter
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)

@NamedQueries({@NamedQuery(name = "Product.findAllProductByNameDescending",
                query = "select p from Product p order by p.productName DESC")}
)
public class Product {   //  parent -- anology with  post from blog application



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    @Column(name = "product_id")
    private Long  productId;

    @Column(name = "product_name")
    private String productName;

	String barcode;

	String brand;

    Long quantity;

	double pricePerUnit;

	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	
	@ManyToMany(mappedBy = "products" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	private List<Vendor> vendors = new ArrayList<>();

	
	  @ManyToOne(fetch = FetchType.EAGER)
	  @JoinColumn(name  = "category_id" , referencedColumnName = "category_id" )
	  private Category category;


	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Purchase> purchases = new ArrayList<>();  //child

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<Orders> orders = new ArrayList<>();  //child

	@ManyToMany(mappedBy = "products" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Customer> customers = new ArrayList<>();

	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<ProductTransactionLog> productTransactionLog = new ArrayList<>();  //child

	public List<ProductTransactionLog> getProductTransactionLog() {
		return productTransactionLog;
	}

	public void setProductTransactionLog(List<ProductTransactionLog> productTransactionLog) {
		this.productTransactionLog = productTransactionLog;
	}

	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
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


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public double getPricePerUnit() {
		return pricePerUnit;
	}


	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
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


	public List<Vendor> getVendors() {
		return vendors;
	}


	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	

	  
	  
	  
	  
	  
	  
	

	
}
