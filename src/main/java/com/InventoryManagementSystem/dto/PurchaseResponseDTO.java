package com.InventoryManagementSystem.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PurchaseResponseDTO {


    PurchaseDTO purchase;


    VendorDTO vendor;


    ProductDTO product;


    CategoryDto category;









}
