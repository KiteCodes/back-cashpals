package com.jea.cashpals.service;

import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.PartyMapper;
import com.jea.cashpals.repository.PartyRepository;
import com.jea.cashpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
    @Autowired
    PartyRepository partyRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PartyMapper partyMapper;

    public PartyDTO createParty( PartyDTO partyDTO){

        //        Party party = new Party();
//        party.setDescription(partyDTO.getDescription());
//        party.setName(partyDTO.getName());
//        party.setOwner(userRepository.findUserById(partyDTO.getOwnerId()));

        User user = userRepository.findUserById(partyDTO.getOwnerId());
        Party party = partyRepository.save(partyMapper.fromPartyDTO(partyDTO,user));

        return partyMapper.fromParty(party);

    }
}
