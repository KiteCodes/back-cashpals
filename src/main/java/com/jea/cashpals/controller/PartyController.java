package com.jea.cashpals.controller;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.repository.PartyRepository;
import com.jea.cashpals.repository.UserRepository;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/party")
public class PartyController {

    @Autowired
    PartyRepository partyRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Party> getParties(){
        List<Party> parties=partyRepository.findAll();
        return parties;
    }
    @GetMapping(path = "/{id}")
    public Party getPartyById(Integer id){
        Party party = partyRepository.findPartyById(id);
        return party;
    }
    @PostMapping
    public ResponseEntity<String> createParty(@RequestBody PartyDTO partyDTO) {
        Party party = new Party();
        party.setDescription(partyDTO.getDescription());
        party.setName(partyDTO.getName());
        party.setOwner(userRepository.findUserById(partyDTO.getOwnerId()));
        partyRepository.save(party);
        return ResponseEntity.ok("Party created");
    }


}
