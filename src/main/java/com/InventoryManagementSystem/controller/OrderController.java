package com.InventoryManagementSystem.controller;


import com.InventoryManagementSystem.dao.OrderProductCustomer;
import com.InventoryManagementSystem.dto.OrderCustomerProductResponseDTO;
import com.InventoryManagementSystem.dto.OrderRequestDTO;
import com.InventoryManagementSystem.dto.ValidateResponse;
import com.InventoryManagementSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/order")

public class OrderController {



    @Autowired
    OrderService orderService;


    @Autowired
    ValidateResponse validateResponse;
    @PostMapping("/createOrder")


    //http://localhost:8080/api/order/createOrder
    public ResponseEntity<OrderRequestDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){


        OrderRequestDTO orderRequestDTO1 =orderService.createOrder(orderRequestDTO);




        return new ResponseEntity<>(orderRequestDTO1, HttpStatus.CREATED);



    }

    //http://localhost:8080/api/order/getOrderDetailsByOrderId/1
    @GetMapping("/getOrderDetailsByOrderId/{orderId}")
    public ResponseEntity<OrderCustomerProductResponseDTO> getOrderDetailsById(@PathVariable Long orderId){

        OrderCustomerProductResponseDTO orderCustomerProductResponseDTO =   orderService.getOrderDetailsById(orderId);


        return new ResponseEntity<>(orderCustomerProductResponseDTO, HttpStatus.OK);
    }


    //http://localhost:8080/api/order/getAllOrderDetails
    @GetMapping("/getAllOrderDetails")
    public ResponseEntity<List<OrderCustomerProductResponseDTO>> getAllOrderDetails(){

     List<OrderCustomerProductResponseDTO> orderCustomerProductResponseDTO =   orderService.getAllOrderDetails();


        return new ResponseEntity<>(orderCustomerProductResponseDTO, HttpStatus.OK);
    }


    //http://localhost:8080/api/order/getOrderDetails
    @GetMapping("/getOrderDetails")
    public ResponseEntity<List<OrderProductCustomer>> getOrderDetails() {
        List<OrderProductCustomer> orderDetails = orderService.getOrderDetails();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

}
