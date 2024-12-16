package com.jea.cashpals.service;

import com.jea.cashpals.dto.EventDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Transaction;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.EventMapper;
import com.jea.cashpals.repository.EventRepository;
import com.jea.cashpals.repository.PartyRepository;
import com.jea.cashpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PartyRepository partyRepository;

    @Autowired
    EventMapper eventMapper;

    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = new Event();
        List<User> users = new ArrayList<>();
        event.setDescription(eventDTO.getDescription());
        event.setName(eventDTO.getName());
        event.setParty(partyRepository.findPartyById(eventDTO.getPartyId()));
        event.setCreator(userRepository.findUserById(eventDTO.getCreatorId()));
        event.setPrice(eventDTO.getPrice());
        eventDTO.getUsersIds().forEach(userId -> {
            users.add(userRepository.findUserById(userId));
        });
        event.setMemberList(users);
        event.getMemberList().add(event.getCreator());
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
        event.setCreator(userRepository.findUserById(eventDTO.getCreatorId()));
        event.setPrice(eventDTO.getPrice());
        eventDTO.getUsersIds().forEach(userId -> event.getMemberList().add(userRepository.findUserById(userId)));
        eventRepository.save(event);
        return eventMapper.fromEvent(event, eventDTO.getCreatorId(), eventDTO.getTransactionIds(),eventDTO.getUsersIds());
    }

    public List<EventDTO> getEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOS = new ArrayList<>();
        events.forEach(event -> eventDTOS.add(eventMapper.fromEvent(event,event.getCreator().getId(), event.getTransactionList().stream().map(Transaction::getId).toList(),event.getMemberList().stream().map(User::getId).toList())));
        return eventDTOS;
    }

    public EventDTO getEventById(Integer id) {
        Event event = eventRepository.findEventById(id);
        return eventMapper.fromEvent(event, event.getCreator().getId(), event.getTransactionList().stream().map(Transaction::getId).toList(),event.getMemberList().stream().map(User::getId).toList());
    }

    public List<EventDTO> getEventsByParty(Integer partyId) {
        List<Event> events = new ArrayList<>();
        events = eventRepository.findEventByPartyId(partyId);
        return events.stream().map(event -> eventMapper.fromEvent(event, event.getCreator().getId(), event.getTransactionList().stream().map(Transaction::getId).toList(),event.getMemberList().stream().map(User::getId).toList())).toList();
    }

    public List<Event> getEventsById(List<Integer> ids) {
        List<Event> events = new ArrayList<>();
        ids.forEach(id -> events.add(eventRepository.findEventById(id)));
        return events;

    }
}
