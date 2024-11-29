package com.jea.cashpals.controller;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.repository.EventRepository;
import com.jea.cashpals.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;
    @Autowired
    PartyRepository partyRepository;

    @GetMapping
    public List<Event> getEvents(){
        List<Event> events=eventRepository.findAll();
        return events;
    }
    @GetMapping(path = "/{id}")
    public Event getEventById(Integer id){
        Event event = eventRepository.findEventById(id);
        return event;
    }
    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventDTO eventDTO) {
        Event event = new Event();
        event.setDescription(eventDTO.getDescription());
        event.setName(eventDTO.getName());
        event.setParty(partyRepository.findPartyById(eventDTO.getPartyId()));
        eventRepository.save(event);
        return ResponseEntity.ok("Event created");
    }
}
