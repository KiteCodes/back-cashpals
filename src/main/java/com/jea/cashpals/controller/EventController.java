package com.jea.cashpals.controller;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Transaction;
import com.jea.cashpals.mapper.EventMapper;
import com.jea.cashpals.repository.EventRepository;
import com.jea.cashpals.repository.PartyRepository;
import com.jea.cashpals.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping
    public List<EventDTO> getEvents(){
        return eventService.getEvents();
    }
    @GetMapping(path = "/{id}")
    public EventDTO getEventById(@PathVariable Integer id){
        return eventService.getEventById(id);
    }
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        EventDTO event = eventService.createEvent(eventDTO);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Integer id, @RequestBody EventDTO eventDTO) {
        EventDTO event = eventService.updateEvent(id, eventDTO);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
