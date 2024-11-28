package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.MierderGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<MierderGroup,Integer> {

   // Group getGroupById(Integer id);
    //Group findGroupById(Integer id);
}
