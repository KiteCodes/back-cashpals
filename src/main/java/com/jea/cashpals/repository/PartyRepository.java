package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party,Integer> {

    Party findPartyById(Integer id);
}
