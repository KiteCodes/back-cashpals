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

    public void createParty( PartyDTO partyDTO){

        User user = userRepository.findUserById(partyDTO.getOwnerId());

        Party party = new Party();
        party.setOwner(user);
        party.setName(partyDTO.getName());
        party.setDescription(partyDTO.getDescription());
        List<User> usersList = new ArrayList<>();
        for (int j=0; j < partyDTO.getUsersIds().size(); j++) {
            usersList.add(userRepository.findUserById(partyDTO.getUsersIds().get(j)));
        }
        party.setUserList(usersList);
        partyRepository.save(party);
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


    public List<UserDTO> addPartyMembers(List<Integer> usersIds, Integer id) {
            Party party = partyRepository.findPartyById(id);
            if (party == null) {
                throw new IllegalArgumentException("Party not found for ID: " + id);
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
