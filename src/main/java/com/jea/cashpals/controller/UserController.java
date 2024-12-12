package com.jea.cashpals.controller;



import com.jea.cashpals.dto.SimpleUserDTO;
import com.jea.cashpals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jea.cashpals.dto.ContactDTO;
import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.repository.UserRepository;
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
    public List<UserDTO> getUserList(){
        return userService.getAllUsers();
    }
    @GetMapping(path = "/{id}")
    public UserDTO getUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userRequest) {
        UserDTO user = userService.updateUser(id, userRequest);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/contacts")
    public void saveContact(@RequestBody ContactDTO contactDTO){
        User user = userRepository.findUserById(contactDTO.getId());
        List<Integer> ids = contactDTO.getContactIDs();
        List<User> userList = userService.getUserList(user.getId(), ids);
        user.setContactList(userList);
        userRepository.save(user);
    }

    @GetMapping(path = "/contacts")
    public List<UserDTO> getContacts(@RequestParam("id") Integer id){
        User user = userRepository.findUserById(id);
        return userService.getContactList(user.getContactList());
    }
    @GetMapping(path = "/party/{id}")
    public List<SimpleUserDTO> getUsersByPartyId(@PathVariable Integer id){
        return userService.getUsersByPartyId(id);

    }
}
