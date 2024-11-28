package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt,Integer> {

}
