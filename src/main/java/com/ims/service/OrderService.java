package com.ims.service;


import com.ims.bo.OrderBO;
import com.ims.dao.OrderProductCustomer;
import com.ims.dto.*;
import com.ims.entity.Customer;
import com.ims.entity.Orders;
import com.ims.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderService {


    @Autowired
     private OrderBO orderBO;

    public OrderRequestDTO createOrder(OrderRequestDTO orderRequestDTO) {


        OrderRequestDTO orderRequestDTO1=  orderBO.createOrder(orderRequestDTO);

      return   orderRequestDTO1;
    }

    public OrderCustomerProductResponseDTO getOrderDetailsById(Long orderId) {



        Orders orders= orderBO.getOrderDetailsById(orderId);


        OrderCustomerProductResponseDTO orderCustomerProductResponseDTO = new OrderCustomerProductResponseDTO();





        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        orderResponseDTO.setOrderedAmount(orders.getOrderAmount());
        orderResponseDTO.setOrderedQuantity(orders.getOrderedQuantity());
        orderResponseDTO.setCreatedAt(orders.getCreatedAt());





        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        Product product = orders.getProduct();
        productResponseDTO.setProductName(product.getProductName());
        productResponseDTO.setBrand(product.getBrand());
        productResponseDTO.setPricePerUnit(product.getPricePerUnit());


        orderResponseDTO.setProduct(productResponseDTO);


        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        Customer customer = orders.getCustomer();

        customerResponseDTO.setCustomerName(customer.getCustomerName());
        customerResponseDTO.setCustomerPhone(customer.getCustomerPhone());

        orderResponseDTO.setCustomer(customerResponseDTO);




        orderCustomerProductResponseDTO.setOrder(orderResponseDTO);

        return orderCustomerProductResponseDTO;
    }

    public List<OrderCustomerProductResponseDTO> getAllOrderDetails() {
      List<Orders> allOrders =   orderBO.getAllOrderDetails();

        List<OrderCustomerProductResponseDTO>  orderCustomerProductResponseDTOS = new ArrayList<>();


         for (Orders orders :allOrders){


             OrderCustomerProductResponseDTO orderCustomerProductResponseDTO = new OrderCustomerProductResponseDTO();





             OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

             orderResponseDTO.setOrderedAmount(orders.getOrderAmount());
             orderResponseDTO.setOrderedQuantity(orders.getOrderedQuantity());
             orderResponseDTO.setCreatedAt(orders.getCreatedAt());





             ProductResponseDTO productResponseDTO = new ProductResponseDTO();
             Product product = orders.getProduct();
             productResponseDTO.setProductName(product.getProductName());
             productResponseDTO.setBrand(product.getBrand());
             productResponseDTO.setPricePerUnit(product.getPricePerUnit());


             orderResponseDTO.setProduct(productResponseDTO);


             CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
             Customer customer = orders.getCustomer();

             customerResponseDTO.setCustomerName(customer.getCustomerName());
             customerResponseDTO.setCustomerPhone(customer.getCustomerPhone());

             orderResponseDTO.setCustomer(customerResponseDTO);




             orderCustomerProductResponseDTO.setOrder(orderResponseDTO);


             orderCustomerProductResponseDTOS.add(orderCustomerProductResponseDTO);
         }

      return  orderCustomerProductResponseDTOS;

    }

    public List<OrderProductCustomer> getOrderDetails() {

        List<OrderProductCustomer> orderProductCustomers =      orderBO.getOrderDetails();

        return orderProductCustomers;
    }


}
