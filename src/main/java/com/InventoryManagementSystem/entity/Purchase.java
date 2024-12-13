package com.InventoryManagementSystem.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Data
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_id")
	private  Long purchaseId;




   Long purchasedQuantity;





    @ManyToOne
    @JoinColumn(name  = "vendor_id" , referencedColumnName = "vendor_id")
    private Vendor vendor;  //many purchases could have only vendor  so only one vendor here object


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

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}


	public long getPurchasedQuantity() {
		return purchasedQuantity;
	}

	public void setPurchasedQuantity(long purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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
