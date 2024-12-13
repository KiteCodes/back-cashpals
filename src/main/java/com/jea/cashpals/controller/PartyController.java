package com.jea.cashpals.controller;

import com.jea.cashpals.dto.PartyDTO;

import com.jea.cashpals.dto.UserDTO;
import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/party")
public class PartyController {

    @Autowired
    PartyService partyService;

    @GetMapping
    public ResponseEntity<List<PartyDTO>> getParties(){
        return new ResponseEntity<>(partyService.getAllParties(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PartyDTO> getPartyById(@PathVariable Integer id){
        return new ResponseEntity<>(partyService.getPartyById(id),HttpStatus.OK);
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
    @PutMapping(path = "/{partyId}/addUsers")
    public ResponseEntity<List<UserDTO>> addPartyUsers(@RequestBody PartyDTO partyDTO, @PathVariable Integer partyId) {
        return new ResponseEntity<>(partyService.addPartyMembers(partyDTO.getUsersIds(), partyId),HttpStatus.OK);
    }
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<List<PartyDTO>> getPartiesByUserId(@PathVariable Integer id) {
        return new ResponseEntity<>(partyService.getPartiesByUserId(id),HttpStatus.OK);
    }

}
