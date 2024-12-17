package com.jea.cashpals.service;

import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.dto.SimpleUserDTO;
import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.UserMapper;
import com.jea.cashpals.repository.PartyRepository;
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

    @Autowired
    PartyRepository partyRepository;

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
    public List<SimpleUserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<SimpleUserDTO> simpleUsers = new ArrayList<>();

        users.forEach(user -> {
            SimpleUserDTO simpleUser = new SimpleUserDTO();
            simpleUser.setId(user.getId());
            simpleUser.setUsername(user.getUsername());
            simpleUser.setFirstName(user.getFirstName());
            simpleUser.setLastName(user.getLastName());
            simpleUser.setEmail(user.getEmail());
            simpleUsers.add(simpleUser);
        });
        return simpleUsers;
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
        userList.remove(user);
        userContactList.addAll(userList);

        return userContactList;
    }

    public List<SimpleUserDTO> getContactList(List<User> contactList) {
        List<SimpleUserDTO> contactDTOList = new ArrayList<>();
        contactList.forEach(contact -> {
            SimpleUserDTO SimpleuserDTO = new SimpleUserDTO();
            SimpleuserDTO.setId(contact.getId());
            SimpleuserDTO.setUsername(contact.getUsername());
            SimpleuserDTO.setFirstName(contact.getFirstName());
            SimpleuserDTO.setLastName(contact.getLastName());
            SimpleuserDTO.setEmail(contact.getEmail());
            contactDTOList.add(SimpleuserDTO);
        });
        return contactDTOList;
    }
    public List<User> getUsersById(List<Integer> ids) {
        return userRepository.findAllById(ids);
    }

    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public List<SimpleUserDTO> getUsersByPartyId(Integer id) {
        List<SimpleUserDTO> simpleUsers = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Party party = partyRepository.findPartyById(id);
        party.getUserList().forEach(user -> userList.add(user));

        userList.forEach(user -> {
            SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
            simpleUserDTO.setId(user.getId());
            simpleUserDTO.setUsername(user.getUsername());
            simpleUserDTO.setFirstName(user.getFirstName());
            simpleUserDTO.setLastName(user.getLastName());
            simpleUserDTO.setEmail(user.getEmail());
            simpleUsers.add(simpleUserDTO);
        });

        return simpleUsers;
    }
}
