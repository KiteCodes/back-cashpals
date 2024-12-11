package com.jea.cashpals.service;

import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.PartyMapper;
import com.jea.cashpals.mapper.UserMapper;
import com.jea.cashpals.repository.PartyRepository;
import com.jea.cashpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PartyService {
    @Autowired
    PartyRepository partyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PartyMapper partyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    public PartyDTO createParty( PartyDTO partyDTO){

        List<User> users = userService.getUsersById(partyDTO.getUsersIds());
        User owner = userService.getUserById(partyDTO.getOwnerId());
        Party party = partyMapper.fromPartyDTO(partyDTO,owner,users);

       // User owner = userRepository.findUserById(partyDTO.getOwnerId());

//        Party party = new Party();
//        party.setOwner(owner);
//        party.setName(partyDTO.getName());
//        party.setDescription(partyDTO.getDescription());
//        List<User> usersList = new ArrayList<>();
//        if (partyDTO.getUsersIds()!=null && !partyDTO.getUsersIds().isEmpty()) {
//            for (int i=0; i < partyDTO.getUsersIds().size(); i++) {
//                usersList.add(userRepository.findUserById(partyDTO.getUsersIds().get(i)));
//            }
//        }
//        usersList.add(owner);
//        party.setUserList(usersList);
//        partyRepository.save(party);
        //partyMapper.fromParty(party);
        return null;
    }

    public PartyDTO updateParty(Integer id, PartyDTO partyRequest) {
        Party party = partyRepository.findPartyById(id);

        party.setOwner(userRepository.findUserById(partyRequest.getOwnerId()));
        party.setName(partyRequest.getName());
        party.setDescription(partyRequest.getDescription());

        return partyMapper.fromParty(partyRepository.save(party));
    }
    public void deleteParty(Integer id) {
        partyRepository.delete(partyRepository.findPartyById(id));
    }
    public List<PartyDTO> getParties(){
        List<Party> parties = partyRepository.findAll();
        List<PartyDTO> partyDTOs = parties.stream().map(partyMapper::fromParty).toList();

        return partyDTOs;
    }
    public Party getPartyById(Integer id){
        return partyRepository.findPartyById(id);
    }


    public List<UserDTO> addPartyMembers(List<Integer> usersIds, Integer partyId) {
            Party party = partyRepository.findPartyById(partyId);
            if (party == null) {
                throw new IllegalArgumentException("Party not found for ID: " + partyId);
            }
            List<User> users = usersIds.stream()
                    .map(userRepository::findUserById)
                    .filter(Objects::nonNull)
                    .toList();

            if (users.isEmpty()) {
                throw new IllegalArgumentException("No valid users found for the given IDs");
            }

            party.getUserList().addAll(users);

            partyRepository.save(party);

            return users.stream()
                    .map(userMapper::fromUser)
                    .toList();
        }


}
