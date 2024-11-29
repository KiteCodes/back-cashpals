package com.jea.cashpals.controller;

import com.jea.cashpals.entitiy.Party;
import com.jea.cashpals.repository.PartyRepository;
import com.jea.cashpals.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class PartyController {

    @Autowired
    PartyRepository partyRepository;

    @Autowired
    PartyService partyService;

    @GetMapping
    public List<Party> getAllGroups () {
        return partyRepository.findAll();
    }

//    @GetMapping(path = "/{id}")
//    public Party getGroupByOwnerId (@PathVariable Integer ownerId) {
//        return partyRepository.findPartyByUserId(ownerId);
//    }
}
