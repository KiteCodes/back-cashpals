package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findEventById(Integer id);

}
