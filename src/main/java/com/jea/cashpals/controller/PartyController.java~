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
    PartyService partyService;

    @GetMapping
    public List<PartyDTO> getParties(){
        return partyService.getParties();
    }
    @GetMapping(path = "/{id}")
    public PartyDTO getPartyById(@PathVariable Integer id){
        return partyService.getPartyById(id);
    }
    @PostMapping
    public ResponseEntity<PartyDTO> createParty(@RequestBody PartyDTO partyDTO) {
        return new ResponseEntity<>(partyService.createParty(partyDTO),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PartyDTO> updateParty(@PathVariable Integer id, @RequestBody PartyDTO partyDTO) {
        return new ResponseEntity<>(partyService.updateParty(id, partyDTO),HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable Integer id) {
        partyService.deleteParty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
