package com.jea.cashpals.service;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.mapper.EventMapper;
import com.jea.cashpals.repository.EventRepository;
import com.jea.cashpals.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    PartyRepository partyRepository;
    @Autowired
    EventMapper eventMapper;

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setDescription(eventDTO.getDescription());
        event.setName(eventDTO.getName());
        event.setParty(partyRepository.findPartyById(eventDTO.getPartyId()));
        eventRepository.save(event);
        return eventDTO;
    }
    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
    public EventDTO updateEvent(Integer id, EventDTO eventDTO) {
        Event event = eventRepository.findEventById(id);
        event.setDescription(eventDTO.getDescription());
        event.setName(eventDTO.getName());
        event.setParty(partyRepository.findPartyById(eventDTO.getPartyId()));
        eventRepository.save(event);
        return eventMapper.fromEvent(event);
    }
    public List<EventDTO> getEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventMapper::fromEvent).toList();
    }
    public EventDTO getEventById(Integer id) {
        Event event = eventRepository.findEventById(id);
        return eventMapper.fromEvent(event);
    }
}
