package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation,Integer> {
}
