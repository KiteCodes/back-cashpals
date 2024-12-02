package com.jea.cashpals.controller;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.dto.PartyDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.PartyMapper;
import com.jea.cashpals.repository.PartyRepository;
import com.jea.cashpals.repository.UserRepository;
import com.jea.cashpals.service.PartyService;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @Autowired
    PartyService partyService;
    @Autowired
    PartyMapper partyMapper;
    @GetMapping
    public List<Party> getParties(){
        return partyRepository.findAll();
    }
    @GetMapping(path = "/{id}")
    public Party getPartyById(Integer id){
        return partyRepository.findPartyById(id);
    }
    @PostMapping
    public ResponseEntity<PartyDTO> createParty(@RequestBody PartyDTO partyDTO) {
        return new ResponseEntity<>(partyService.createParty(partyDTO),HttpStatus.CREATED);
    }


}
