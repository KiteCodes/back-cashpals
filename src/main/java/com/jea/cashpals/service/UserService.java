package com.jea.cashpals.service;

import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public User saveUser( UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        String encryptedPassword = encoder.encode(userDTO.getPassword());
        user.setPassword(encryptedPassword);
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        return userRepository.save(user);
    }
}
