package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findEventById(Integer id);

    Transaction findTransactionById(Integer id);

    List<Transaction> findTransactionByEventId(Integer id);
}
