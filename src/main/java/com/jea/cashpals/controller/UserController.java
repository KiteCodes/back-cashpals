package com.jea.cashpals.controller;

import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.repository.UserRepository;

import com.jea.cashpals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping(path = "/{id}")
    public User getUserById(Integer id){
        return userRepository.findUserById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userRequest) {
        UserDTO user = userService.updateUser(id, userRequest);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
