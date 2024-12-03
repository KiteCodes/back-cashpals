package com.jea.cashpals.controller;

import com.jea.cashpals.dto.TransactionDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Transaction;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.repository.TransactionRepository;
import com.jea.cashpals.repository.UserRepository;
import com.jea.cashpals.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
        return transactionRepository.findEventById(id);
    }

    @PostMapping
    public ResponseEntity<List<Transaction>> createTransactions(@RequestBody TransactionDTO transactionDTO) {
        transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/debtor/{id}")
    public List<Transaction> getTransactionByDebtorId(@PathVariable Integer id) {
        User user = userRepository.findUserById(id);
    return user.getDebtorTransactions();
    }

    @GetMapping(path = "/indebted/{id}")
        public List<Transaction>getTransactionByIndebtedId(@PathVariable Integer id){
        User user = userRepository.findUserById(id);
        return user.getIndebtedTransactions();
    }
}
