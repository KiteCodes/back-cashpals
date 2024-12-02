package com.jea.cashpals.controller;

import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/signin")
    public ResponseEntity<String> signIn(UsernamePasswordAuthenticationToken upa) {
        User u = (User) upa.getPrincipal();
        return ResponseEntity.ok().body("{\"resp\":\"Login exitoso\", \"id\":"+u.getId()+"}");
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.ok().body("{\"resp\":\"Se ha registrado correctamente\"}");
    }

}
