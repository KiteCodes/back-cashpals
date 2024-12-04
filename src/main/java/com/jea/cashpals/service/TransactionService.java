package com.jea.cashpals.service;

import com.jea.cashpals.dto.TransactionDTO;
import com.jea.cashpals.entitiy.Transaction;
import com.jea.cashpals.repository.TransactionRepository;
import com.jea.cashpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    public void createTransaction(TransactionDTO transactionDTO) {
        for (Integer indebtedId : transactionDTO.getIndebtedId()) {
            Transaction newTransaction = new Transaction();
            newTransaction.setName(transactionDTO.getName());
            newTransaction.setValue(transactionDTO.getValue());
            newTransaction.setDescription(transactionDTO.getDescription());

            newTransaction.setDebtor(userRepository.findUserById(transactionDTO.getDebtorId()));

            newTransaction.setIndebted(userRepository.findUserById(indebtedId));

            transactionRepository.save(newTransaction);
        }
    }
}
