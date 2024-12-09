package com.jea.cashpals.service;

import com.jea.cashpals.dto.TransactionDTO;
import com.jea.cashpals.entitiy.Transaction;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.TransactionMapper;
import com.jea.cashpals.repository.TransactionRepository;
import com.jea.cashpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionMapper transactionsMapper;

    public List<Transaction> createTransaction(TransactionDTO transactionDTO) {
        List<Transaction> createdTransactions = new ArrayList<>();
        for (Integer indebtedId : transactionDTO.getIndebtedId()) {

            Transaction newTransaction = new Transaction();
            newTransaction.setName(transactionDTO.getName());
            newTransaction.setValue(transactionDTO.getValue());
            newTransaction.setDescription(transactionDTO.getDescription());

            newTransaction.setDebtor(userRepository.findUserById(transactionDTO.getDebtorId()));

            newTransaction.setIndebted(userRepository.findUserById(indebtedId));

            transactionRepository.save(newTransaction);
            createdTransactions.add(newTransaction);

        }
        return createdTransactions;
    }
    public void deleteTransaction(Integer id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        transactionRepository.delete(transaction);
    }
    public TransactionDTO updateTransaction(Integer id, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        transaction.setName(transactionDTO.getName());
        transaction.setValue(transactionDTO.getValue());
        transaction.setDescription(transactionDTO.getDescription());
        transactionRepository.save(transaction);
        return transactionDTO;
    }

    public List<TransactionDTO> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transactionsMapper::fromTransaction).toList();
    }
    public TransactionDTO getTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        return transactionsMapper.fromTransaction(transaction);
    }
    public List<TransactionDTO> getTransactionByDebtorId(Integer id) {
        User user = userRepository.findUserById(id);
        return user.getDebtorTransactions().stream().map(transactionsMapper::fromTransaction).toList();
    }
    public List<TransactionDTO> getTransactionByIndebtedId(Integer id) {
        User user = userRepository.findUserById(id);
        return user.getIndebtedTransactions().stream().map(transactionsMapper::fromTransaction).toList();
    }
}
