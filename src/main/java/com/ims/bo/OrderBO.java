package com.ims.bo;


import com.ims.dao.*;
import com.ims.dto.*;
import com.ims.entity.Customer;
import com.ims.entity.Orders;
import com.ims.entity.Product;
import com.ims.entity.ProductTransactionLog;
import com.ims.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderBO {



    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ValidateResponse validateResponse;

    @Autowired
    ProductTransactionLogRepository productTransactionLogRepository;


    public OrderRequestDTO createOrder(OrderRequestDTO orderRequestDTO) {

        List<String> productMessage = new ArrayList<>() ; // for response



        OrderRequestDTO orderResponse = new OrderRequestDTO();// for response

        CustomerDTO customerResponseDTO = new CustomerDTO(); // for response
        List<ProductDTO>  productResponseDTOs = new ArrayList<>();  // for response


        List<ProductDTO> products = orderRequestDTO.getProducts();
        for (ProductDTO productDTO :products ){


            try{

                Product existingProduct = productRepository.findByProductNameAndBrand(productDTO.getProductName(), productDTO.getBrand())
                        .orElseThrow(() -> new ResourceNotFoundException("product not present " + productDTO.getProductName()));


                CustomerDTO customerDTO = orderRequestDTO.getCustomer();


                Optional<Customer> byCustomerPhoneAndCustomerName = customerRepository.findByCustomerPhoneAndCustomerName(customerDTO.getCustomerPhone(), customerDTO.getCustomerName());



                if (byCustomerPhoneAndCustomerName.isPresent()){


                    Customer existingCustomer = byCustomerPhoneAndCustomerName.get();

                   //  quantity logic

                    try {

                        if (productDTO.getQuantity()> existingProduct.getQuantity()){

                            throw new ResourceNotFoundException("entered quantity is greater than the  stock available " +
                                    ":: current stock :"+existingProduct.getQuantity()  + "for product "+ productDTO.getProductName());
                        }  else {

                            existingProduct.getCustomers().add(existingCustomer);
                            existingCustomer.getProducts().add(existingProduct);

                            customerRepository.save(existingCustomer);  // save which  has join table annotation

                              // add details  to the order  table

                            //calculate price for the order

                           double  orderAmount =(existingProduct.getPricePerUnit())*(productDTO.getQuantity());

                            Orders order = new Orders();

                            order.setOrderedQuantity(productDTO.getQuantity());
                            order.setOrderAmount(orderAmount);  // save order amount
                            order.setCustomer(existingCustomer);
                            existingCustomer.getOrders().add(order);

                            order.setProduct(existingProduct);
                            existingProduct.getOrders().add(order);


                            Orders savedOrder = orderRepository.save(order);


                            ProductTransactionLog productTransactionLog = new ProductTransactionLog();
                            productTransactionLog.setQuantityChanged(productDTO.getQuantity());
                            productTransactionLog.setTransactionType("removed");
                            productTransactionLog.setProduct(existingProduct);
                            existingProduct.getProductTransactionLog().add(productTransactionLog);
                            productTransactionLogRepository.save(productTransactionLog);



                            customerResponseDTO.setCustomerId(existingCustomer.getCustomerId());
                            customerResponseDTO.setCustomerName(existingCustomer.getCustomerName());
                            customerResponseDTO.setCustomerPhone(existingCustomer.getCustomerPhone());
                            customerResponseDTO.setCustomerEmail(existingCustomer.getCustomerEmail());

                            orderResponse.setCustomer(customerResponseDTO);




                           ProductDTO productResponseDTO = new ProductDTO();

                            productResponseDTO.setProductId(existingProduct.getProductId());
                            productResponseDTO.setProductName(existingProduct.getProductName());
                            productResponseDTO.setBrand(existingProduct.getBrand());
                            productResponseDTO.setPricePerUnit(existingProduct.getPricePerUnit());

                            productResponseDTO.setBarcode(existingProduct.getBarcode());




                            OrderDTO orderResponseDTO = new OrderDTO();
                            orderResponseDTO.setOrderId(savedOrder.getOrderId());
                            orderResponseDTO.setOrderdQuantity( savedOrder.getOrderedQuantity());
                            orderResponseDTO.setOrderAmount(savedOrder.getOrderAmount());
                            orderResponseDTO.setCreatedAt(savedOrder.getCreatedAt());

                            productResponseDTO.setOrder(orderResponseDTO);

                            productResponseDTOs.add(productResponseDTO);

                            orderResponse.setProducts(productResponseDTOs);




                        }

                    }catch(ResourceNotFoundException e ){

                        productMessage.add("quantity  doesnt exist  for "+productDTO.getProductName() + productDTO.getBrand());


                    }

                    // mutually add to each others list

                }else {

                    Customer newCustomer = new Customer();

            newCustomer.setCustomerPhone(customerDTO.getCustomerPhone());
            newCustomer.setCustomerName(customerDTO.getCustomerName());


                 //    product must exist to create order
                //mutually add to each others list


                    try {

                        if (productDTO.getQuantity()> existingProduct.getQuantity()){

                            throw new ResourceNotFoundException("entered quantity is greater than the  stock available " +
                                    ":: current stock :"+existingProduct.getQuantity()  + "for product "+ productDTO.getProductName());
                        } else {

                            existingProduct.getCustomers().add(newCustomer);
                            newCustomer.getProducts().add(existingProduct);

                            Customer savedCustomer = customerRepository.save(newCustomer);// save which  has join table annotation
//  calculate orderAmount
                            double  orderAmount =(existingProduct.getPricePerUnit())*(productDTO.getQuantity());
                            Orders order = new Orders();

                            order.setOrderedQuantity(productDTO.getQuantity());
                            order.setOrderAmount(orderAmount);  // save order amount
                            order.setCustomer(newCustomer);
                            newCustomer.getOrders().add(order);

                            order.setProduct(existingProduct);
                            existingProduct.getOrders().add(order);

                            Orders savedOrder = orderRepository.save(order);


                            ProductTransactionLog productTransactionLog = new ProductTransactionLog();
                            productTransactionLog.setQuantityChanged(productDTO.getQuantity());
                            productTransactionLog.setTransactionType("removed");
                            productTransactionLog.setProduct(existingProduct);
                            existingProduct.getProductTransactionLog().add(productTransactionLog);
                            productTransactionLogRepository.save(productTransactionLog);


                            customerResponseDTO.setCustomerId(savedCustomer.getCustomerId());
                            customerResponseDTO.setCustomerName(savedCustomer.getCustomerName());
                            customerResponseDTO.setCustomerPhone(savedCustomer.getCustomerPhone());
                            customerResponseDTO.setCustomerEmail(savedCustomer.getCustomerEmail());

                            orderResponse.setCustomer(customerResponseDTO);




                            ProductDTO productResponseDTO = new ProductDTO();

                            productResponseDTO.setProductId(existingProduct.getProductId());
                            productResponseDTO.setProductName(existingProduct.getProductName());
                            productResponseDTO.setBrand(existingProduct.getBrand());
                            productResponseDTO.setPricePerUnit(existingProduct.getPricePerUnit());
                            productResponseDTO.setBarcode(existingProduct.getBarcode());




                            OrderDTO orderResponseDTO = new OrderDTO();
                            orderResponseDTO.setOrderId(savedOrder.getOrderId());
                            orderResponseDTO.setOrderdQuantity( savedOrder.getOrderedQuantity());
                            orderResponseDTO.setOrderAmount(savedOrder.getOrderAmount());
                            orderResponseDTO.setCreatedAt(savedOrder.getCreatedAt());

                            productResponseDTO.setOrder(orderResponseDTO);

                            productResponseDTOs.add(productResponseDTO);


                            orderResponse.setProducts(productResponseDTOs);
                        }
                    }catch(ResourceNotFoundException e ){

                        productMessage.add("quantity  doesnt exist  for "+productDTO.getProductName() + productDTO.getBrand());



                    }


                }

            } catch(ResourceNotFoundException e ){



                productMessage.add("product  doesnt exist  "+productDTO.getProductName() + productDTO.getBrand());



            }



        }


        if (!productMessage.isEmpty()){
            validateResponse.setProductMessage(productMessage);
            orderResponse.setValidateResponse(validateResponse);
        }


        return orderResponse;
    }

    public Orders getOrderDetailsById(Long orderId) {

      Orders order   = orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("order doesnt exist"));

      return order;

    }

    public List<Orders> getAllOrderDetails() {

        List<Orders> all = orderRepository.findAll();

        return all;

    }

    public List<OrderProductCustomer> getOrderDetails() {

        List<OrderProductCustomer> orderProductCustomerDetails = orderRepository.findOrderProductCustomerDetails();

        return orderProductCustomerDetails;
    }
}


