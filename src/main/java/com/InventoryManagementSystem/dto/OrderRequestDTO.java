package com.InventoryManagementSystem.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderRequestDTO {

    CustomerDTO customer;


    List<ProductDTO> products = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
      ValidateResponse validateResponse;


}
