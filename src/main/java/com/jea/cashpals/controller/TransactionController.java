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
    TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping(path = "/{id}")
    public TransactionDTO getTransactionById(@PathVariable Integer id) {
        return transactionService.getTransactionById(id);
    }

    @PostMapping
    public ResponseEntity<List<Transaction>> createTransactions(@RequestBody TransactionDTO transactionDTO){
        List<Transaction> createdTransactions = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(createdTransactions,HttpStatus.CREATED);
    }

    @GetMapping(path = "/debtor/{id}")
    public ResponseEntity<List<TransactionDTO>> getTransactionByDebtorId(@PathVariable Integer id) {
        return new ResponseEntity<>(transactionService.getTransactionByDebtorId(id),HttpStatus.OK);
    }

    @GetMapping(path = "/indebted/{id}")
    public ResponseEntity<List<TransactionDTO>>getTransactionByIndebtedId(@PathVariable Integer id){
       return new ResponseEntity<>(transactionService.getTransactionByIndebtedId(id),HttpStatus.OK);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Integer id, @RequestBody TransactionDTO transactionDTO) {
        return new ResponseEntity<>(transactionService.updateTransaction(id, transactionDTO),HttpStatus.OK);
    }
}
