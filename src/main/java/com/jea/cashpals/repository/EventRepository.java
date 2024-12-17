package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {
    Event findEventById(Integer id);
    List<Event> findEventByPartyId(Integer id);

}
