package com.InventoryManagementSystem.bo;

import com.InventoryManagementSystem.dto.UserDTO;
import com.InventoryManagementSystem.entity.User;
import com.InventoryManagementSystem.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserBO {
    @Autowired
    UserRepository userRepository;
    public String addUser(UserDTO userDTO) {

        User user  = new User();

        user.setUserName(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User savedUser = userRepository.save(user);

        String message;
        if (savedUser!=null){

             message = "user Registered";
        }else {
           message = "user  not Registered";
        }
       return message;
    }

    public String getUser(UserDTO userDTO) {

        String message;
        Optional<User> user = userRepository.findByUserNameAndPassword(userDTO.getUsername(), userDTO.getPassword());
      if (user.isPresent()){
          message = "verified";
      }else {
          message = "user does not exist ";
      }
    return  message;
    }
}
