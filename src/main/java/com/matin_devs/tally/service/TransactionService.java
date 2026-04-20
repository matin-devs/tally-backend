package com.matin_devs.tally.service;

import com.matin_devs.tally.dto.TransactionRequest;
import com.matin_devs.tally.model.Transaction;
import com.matin_devs.tally.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    public Transaction createTransaction(TransactionRequest request) {

        Transaction transaction = Transaction.builder()
                .timestamp(request.getTimestamp())
                .title(request.getTitle())
                .category(request.getCategory())
                .amount(request.getAmount())
                .build();

        transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction getTransactionById(UUID id) {
        return transactionRepository.getReferenceById(id);
    }

    @Transactional
    public void updateTransactionById(UUID id, TransactionRequest request) {
        Transaction transaction = getTransactionById(id);
        transaction.setTimestamp(request.getTimestamp());
        transaction.setTitle(request.getTitle());
        transaction.setAmount(request.getAmount());
        transaction.setCategory(request.getCategory());
    }

    public void deleteTransactionById(UUID id) {
        transactionRepository.deleteById(id);
    }
}
