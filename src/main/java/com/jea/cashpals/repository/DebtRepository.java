package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Transaction,Integer> {

}
