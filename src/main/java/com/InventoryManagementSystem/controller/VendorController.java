package com.InventoryManagementSystem.controller;


import com.InventoryManagementSystem.dao.VendorRepository;
import com.InventoryManagementSystem.dto.VendorDTO;
import com.InventoryManagementSystem.service.VendorService;
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
@RequestMapping("/api/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {

  @Autowired
    VendorRepository vendorRepository;


  @Autowired
  VendorService vendorService;


  //http://localhost:8080/api/vendor/addVendor
  @PostMapping("/addVendor")
    public ResponseEntity<VendorDTO> addVendor(@Valid @RequestBody VendorDTO vendorDTO){


    VendorDTO savedvendor = vendorService.addVendor(vendorDTO);

    return new ResponseEntity<>(savedvendor, HttpStatus.CREATED);
  }

  //http://localhost:8080/api/vendor/addVendorsList
  @PostMapping("/addVendorsList")
  public ResponseEntity< String> addVendorList(@Valid @RequestBody List<VendorDTO> vendorDTOs){


     vendorService.addVendorList(vendorDTOs);

    return new ResponseEntity<>("savedvendor", HttpStatus.CREATED);
  }


  //http://localhost:8080/api/vendor/getVendorById/1
  @GetMapping("/getVendorById/{vendorId}")
  public ResponseEntity<VendorDTO> getVendorById(@PathVariable @Min(1) @Positive(message = "Vendor ID must be a positive integer") Long vendorId){

    VendorDTO savedvendor =vendorService.getVendorById(vendorId);

    return new ResponseEntity<>(savedvendor, HttpStatus.OK);
  }



  //http://localhost:8080/api/vendor/getVendorName/VendorById/1
  @GetMapping("/getVendorName/VendorById/{vendorId}")
  public ResponseEntity<VendorDTO> getVendorNameByVendorById(@PathVariable @Min(1) @Positive(message = "Vendor ID must be a positive integer") Long vendorId){

    VendorDTO savedvendor =vendorService.getVendorNameByVendorById(vendorId);

    return new ResponseEntity<>(savedvendor, HttpStatus.OK);
  }

  //http://localhost:8080/api/vendor/getAllVendors
  @GetMapping("/getAllVendors")
  public ResponseEntity<List<VendorDTO>> getAllVendors(){

   List<VendorDTO> savedvendors =vendorService.getAllVendors();

    return new ResponseEntity<>(savedvendors, HttpStatus.OK);


  }



  //http://localhost:8080/api/vendor/getAllVendorsId
  @GetMapping("/getAllVendorsId")
  public ResponseEntity<List<VendorDTO>> getAllVendorsId(){

    List<VendorDTO> savedvendors =vendorService.getAllVendorsId();

    return new ResponseEntity<>(savedvendors, HttpStatus.OK);


  }

}
