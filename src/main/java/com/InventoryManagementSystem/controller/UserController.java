package com.InventoryManagementSystem.controller;

import com.InventoryManagementSystem.dto.UserDTO;
import com.InventoryManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/User")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {



    @Autowired
    private UserService userService;




    //http://localhost:8080/api/User/addUser
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser( @RequestBody UserDTO userDTO){


        String message = userService.addUser(userDTO);

        return new ResponseEntity<>(message, HttpStatus.CREATED);


    }


    //http://localhost:8080/api/User/getUser


    @PostMapping("/getUser")
    public ResponseEntity<String> getUser( @RequestBody UserDTO userDTO){


        String message = userService.getUser(userDTO);

        return new ResponseEntity<>(message, HttpStatus.CREATED);


    }

}
