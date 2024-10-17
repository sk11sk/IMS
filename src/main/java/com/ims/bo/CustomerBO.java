package com.ims.bo;

import com.ims.dao.CustomerRepository;
import com.ims.dto.CustomerDTO;
import com.ims.entity.Customer;

import com.ims.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CustomerBO {

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setCustomerPhone(customerDTO.getCustomerPhone());


        Customer  addedCustomer = customerRepository.save(customer);

        return addedCustomer;
    }

    public void addCustomerList(List<CustomerDTO> customerDTOs) {


        for (CustomerDTO customerDTO: customerDTOs){

            Customer customer = new Customer();

            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setCustomerEmail(customerDTO.getCustomerEmail());
            customer.setCustomerPhone(customerDTO.getCustomerPhone());


            Customer  addedCustomer = customerRepository.save(customer);

        }
    }

    public Customer getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("entered customer id not found "));


        return customer;
    }

    public List<Customer> getAllCustomers() {


        List<Customer> all = customerRepository.findAll();

        return all;
    }
}
