package com.jea.cashpals.service;

import com.jea.cashpals.dto.TransactionDTO;
import com.jea.cashpals.entitiy.Event;
import com.jea.cashpals.entitiy.Transaction;
import com.jea.cashpals.entitiy.User;
import com.jea.cashpals.mapper.TransactionMapper;
import com.jea.cashpals.repository.EventRepository;
import com.jea.cashpals.repository.TransactionRepository;
import com.jea.cashpals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionMapper transactionsMapper;

    @Autowired
    EventService eventService;

    public List<TransactionDTO> createTransaction(TransactionDTO transactionDTO) {
        List<Transaction> createdTransactions = new ArrayList<>();

        for (Integer indebtedId : transactionDTO.getIndebtedId()) {
            Transaction newTransaction = new Transaction();
            newTransaction.setEvent(eventRepository.findEventById(transactionDTO.getEventId()));
            newTransaction.setValue(transactionDTO.getValue());
            newTransaction.setDebtor(userRepository.findUserById(transactionDTO.getDebtorId()));
            newTransaction.setIndebted(userRepository.findUserById(indebtedId));

            transactionRepository.save(newTransaction);
            createdTransactions.add(newTransaction);
        }
            List<TransactionDTO> transactionsDTO = new ArrayList<>();
            for(Transaction transaction : createdTransactions) {
                transactionsDTO.add(transactionsMapper.fromTransaction(transaction,
                        transaction.getDebtor().getId(),
                        Collections.singletonList(transaction.getIndebted().getId()),
                        transaction.getEvent().getId()));
            }
        return transactionsDTO;
    }

    public void deleteTransaction(Integer id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        transactionRepository.delete(transaction);
    }

    public TransactionDTO updateTransaction(Integer id, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        transaction.setValue(transactionDTO.getValue());
        transactionRepository.save(transaction);
        return transactionDTO;
    }

    public List<TransactionDTO> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDTO> transactionsDTOS = new ArrayList<>();
        transactions.forEach(transaction -> transactionsDTOS.add(transactionsMapper.fromTransaction(transaction,
                transaction.getDebtor().getId(),
                Collections.singletonList(transaction.getIndebted().getId()),
                transaction.getEvent().getId())));
        return transactionsDTOS;
    }

    public TransactionDTO getTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findTransactionById(id);
        return transactionsMapper.fromTransaction(transaction,
                transaction.getDebtor().getId(),
                Collections.singletonList(transaction.getIndebted().getId()),
                transaction.getEvent().getId());
    }

    public List<TransactionDTO> getTransactionByDebtorId(Integer id) {
        User user = userRepository.findUserById(id);
        return user.getDebtorTransactions().stream()
                .map(transaction -> transactionsMapper.fromTransaction(transaction,
                        transaction.getDebtor().getId(),
                        Collections.singletonList(transaction.getIndebted().getId()),
                        transaction.getEvent().getId()))
                .toList();
    }

    public List<TransactionDTO> getTransactionByIndebtedId(Integer id) {
        User user = userRepository.findUserById(id);
        List<Transaction> transactions = user.getIndebtedTransactions();
        List<TransactionDTO> transactionsDTO = new ArrayList<>();

        transactions.forEach(transaction -> transactionsDTO.add(transactionsMapper.fromTransaction(transaction,
                transaction.getDebtor().getId(),
                Collections.singletonList(transaction.getIndebted().getId()),
                transaction.getEvent().getId())));

        return transactionsDTO;
    }
    public List<TransactionDTO> getTransactionByEventId(Integer id) {
        List<Transaction> transactions = transactionRepository.findTransactionByEventId(id);
        return transactions.stream()
                .map(transaction -> transactionsMapper.fromTransaction(transaction,
                        transaction.getDebtor().getId(),
                        Collections.singletonList(transaction.getIndebted().getId()),
                        transaction.getEvent().getId()))
                .toList();
    }
}
