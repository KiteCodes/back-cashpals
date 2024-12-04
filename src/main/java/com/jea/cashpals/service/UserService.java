package com.jea.cashpals.service;

import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    public void saveUser( UserDTO userDTO) {
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
        userRepository.save(user);
    }
    public UserDTO updateUser(Integer id, UserDTO userRequest) {
        User user = userRepository.findUserById(id);

        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setUsername(userRequest.getFirstName());
        user.setPhone(userRequest.getPhone());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());

        return userMapper.fromUser(userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        userRepository.delete(user);
    }
}
