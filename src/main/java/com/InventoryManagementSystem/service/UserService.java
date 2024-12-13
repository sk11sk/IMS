package com.InventoryManagementSystem.service;

import com.InventoryManagementSystem.dto.UserDTO;
import com.InventoryManagementSystem.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    @Autowired
    UserBO userBO;


    public String addUser(UserDTO userDTO) {


      String message =  userBO.addUser(userDTO);

        return message;
    }

    public String getUser(UserDTO userDTO) {

        String message =   userBO.getUser(userDTO);

        return message;
    }
}
