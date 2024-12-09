package com.jea.cashpals.repository;

import com.jea.cashpals.entitiy.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findEventById(Integer id);

    Transaction findTransactionById(Integer id);
}
