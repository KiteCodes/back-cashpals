package com.jea.cashpals.controller;

import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/party")
public class PartyController {

    @Autowired
    private PartyRepository partyRepository;

    @GetMapping
    public List<Party> getParties(){
        return partyRepository.findAll();
    }
    @GetMapping(path = "/{id}")
    public Party getPartyById(Integer id){
        return partyRepository.findPartyById(id);
    }
    @PostMapping
    private ResponseEntity<String> createParty(@RequestBody PartyDTO party){
        Party newParty = new Party();
        newParty.setName(party.getName());
        newParty.setDescription(party.getDescription());
       //TODO:terminar esto cuando este linkeado a user
        // newGroup.set(group.getOwnerId());
        return ResponseEntity.ok("Party created");
    }
    //TODO: CREATE GROUP
    //TODO: FIND GROUPS BY USER (ID)

}
