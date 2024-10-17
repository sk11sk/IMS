package com.ims.controller;

import com.ims.dao.CustomerRepository;
import com.ims.dao.VendorRepository;
import com.ims.dto.CustomerDTO;
import com.ims.dto.VendorDTO;
import com.ims.service.CustomerService;
import com.ims.service.VendorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/api/customer")
public class CustomerController {


        @Autowired
      private  CustomerRepository customerRepository;


        @Autowired
        private CustomerService customerService;


        //http://localhost:8080/api/customer/addVendor
        @PostMapping("/addCustomer")
        public ResponseEntity<CustomerDTO> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){


            CustomerDTO savedCustomer = customerService.addCustomer(customerDTO);

            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);


        }

        //http://localhost:8080/api/customer/addCustomersList
        @PostMapping("/addCustomersList")
        public ResponseEntity< String> addCustomerList(@Valid @RequestBody List<CustomerDTO> customerDTOs){


            customerService.addCustomerList(customerDTOs);

            return new ResponseEntity<>("added", HttpStatus.CREATED);
        }


        //http://localhost:8080/api/customer/getCustomerIdById/1
        @GetMapping("/getCustomerIdById/{customerId}")
        public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable @Min(1) @Positive(message = "Customer ID must be a positive integer") Long customerId){

            CustomerDTO savedCustomer =customerService.getCustomerById(customerId);

            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        }

        //http://localhost:8080/api/customer/getAllCustomers
        @GetMapping("/getAllCustomers")
        public ResponseEntity<List<CustomerDTO>> getAllCustomers(){

            List<CustomerDTO> savedCustomers =customerService.getAllCustomers();

            return new ResponseEntity<>(savedCustomers, HttpStatus.OK);


        }




    }

