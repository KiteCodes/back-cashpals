package com.jea.cashpals.service;

import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.UserMapper;
import com.jea.cashpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::fromUser).toList();
    }

    public UserDTO findUserById(Integer id) {
        return userMapper.fromUser(userRepository.findUserById(id));
    }

    public List<User> getUserList(Integer id, List<Integer> contactIDs){
        List<User> userList = new ArrayList<>();
        User user = userRepository.findUserById(id);
        List<User> userContactList = user.getContactList();
        contactIDs.forEach(currentId -> {
            userList.add(userRepository.findUserById(currentId));
        });

        userList.removeAll(userContactList);
        userContactList.addAll(userList);

        return userContactList;
    }

    public List<UserDTO> getContactList(List<User> contactList) {
        List<UserDTO> contactDTOList = new ArrayList<>();
        contactList.forEach(contact -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(contact.getUsername());
            userDTO.setFirstName(contact.getFirstName());
            userDTO.setLastName(contact.getLastName());
            userDTO.setEmail(contact.getEmail());
            userDTO.setPhone(contact.getPhone());
            contactDTOList.add(userDTO);
        });
        return contactDTOList;
    }
}
