package com.jea.cashpals.controller;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.repository.EventRepository;
import com.jea.cashpals.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PartyRepository groupRepository;
    @GetMapping
    public List<Event> getEvents(){
        return eventRepository.findAll();
    }
    @GetMapping(path = "/{id}")
    public Event getEventById(Integer id){
        return eventRepository.findEventById(id);
    }
    @PostMapping
    private ResponseEntity<String>  createEvent(@RequestBody EventDTO event){
        Event newEvent= new Event();
        newEvent.setName(event.getName());
        newEvent.setDescription(event.getDescription());
       // newEvent.setGroup(groupRepository.findGroupById(event.getGroupId()));
        return ResponseEntity.ok("Event created");
    }
//    @GetMapping("/{id}")
//    public List<Event> getEventsByGroup(Integer id){
//        return eventRepository.findEventsByGroupId(id);
//    }
}