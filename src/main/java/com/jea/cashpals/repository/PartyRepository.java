package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Party;

import com.jea.cashpals.entitiy.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party,Integer> {
    Party findPartyById(Integer id);

    User findUserById(int Id);
}