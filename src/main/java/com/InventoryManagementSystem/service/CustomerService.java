package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.bo.CustomerBO;
import com.InventoryManagementSystem.dto.CustomerDTO;
import com.InventoryManagementSystem.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {


    @Autowired
    CustomerBO customerBO;

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer addedCustomer = customerBO.addCustomer(customerDTO);

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setCustomerId(addedCustomer.getCustomerId());
        customerDTO1.setCustomerName(addedCustomer.getCustomerName());
        customerDTO1.setCustomerPhone(addedCustomer.getCustomerPhone());
        customerDTO1.setCustomerEmail(addedCustomer.getCustomerEmail());
        customerDTO1.setCreatedAt(addedCustomer.getCreatedAt());
        customerDTO1.setUpdatedAt(addedCustomer.getUpdatedAt());
        return customerDTO1;


    }

    public void addCustomerList(List<CustomerDTO> customerDTOs) {
        customerBO.addCustomerList(customerDTOs);


    }

    public CustomerDTO getCustomerById(Long customerId) {

        Customer customerById = customerBO.getCustomerById(customerId);

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setCustomerId(customerById.getCustomerId());
        customerDTO1.setCustomerName(customerById.getCustomerName());
        customerDTO1.setCustomerPhone(customerById.getCustomerPhone());
        customerDTO1.setCustomerEmail(customerById.getCustomerEmail());
        customerDTO1.setCreatedAt(customerById.getCreatedAt());
        customerDTO1.setUpdatedAt(customerById.getUpdatedAt());
        return customerDTO1;

    }


    public List<CustomerDTO> getAllCustomers() {


        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> allCustomers = customerBO.getAllCustomers();


        for (Customer customer : allCustomers) {

            CustomerDTO customerDTO1 = new CustomerDTO();
            customerDTO1.setCustomerId(customer.getCustomerId());
            customerDTO1.setCustomerName(customer.getCustomerName());
            customerDTO1.setCustomerPhone(customer.getCustomerPhone());
            customerDTO1.setCustomerEmail(customer.getCustomerEmail());
            customerDTO1.setCreatedAt(customer.getCreatedAt());
            customerDTO1.setUpdatedAt(customer.getUpdatedAt());

            customerDTOS.add(customerDTO1);


        }

        return customerDTOS;
    }

    public List<CustomerDTO> getAllCustomersDetails() {

        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> allCustomers = customerBO.getAllCustomers();


        for (Customer customer : allCustomers) {

            CustomerDTO customerDTO1 = new CustomerDTO();

            customerDTO1.setCustomerName(customer.getCustomerName());
            customerDTO1.setCustomerPhone(customer.getCustomerPhone());
            customerDTO1.setCustomerEmail(customer.getCustomerEmail());


            customerDTOS.add(customerDTO1);


        }

        return customerDTOS; }
}
