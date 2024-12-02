package com.jea.cashpals.controller;

import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.repository.UserRepository;

import com.jea.cashpals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping(path = "/{id}")
    public User getUserById(Integer id){
        return userRepository.findUserById(id);
    }
    @PostMapping(path = "/contacts/")
    public void saveContact(@RequestBody Integer id, List<Integer> contactIDs){
        User user = userRepository.findUserById(id);
        user.setContactList(userService.getUserList(id, contactIDs));
    }
    @GetMapping(path = "/contacts")
    public List<User> getContacts(@RequestBody Integer id){
        User user = userRepository.findUserById(id);
        return user.getContactList();
    }
}
