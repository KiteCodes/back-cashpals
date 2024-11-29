package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Party;
import jakarta.servlet.http.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party,Integer> {
    Party findPartyById(Integer id);
    Party findPartyByUserId(Integer userId);
}
