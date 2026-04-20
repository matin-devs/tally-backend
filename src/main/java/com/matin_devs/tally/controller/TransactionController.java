package com.matin_devs.tally.controller;

import com.matin_devs.tally.dto.TransactionRequest;
import com.matin_devs.tally.exception.TransactionNotFoundException;
import com.matin_devs.tally.model.Transaction;
import com.matin_devs.tally.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping()
    public ResponseEntity<String> getTransactionByUserId(@RequestParam UUID id) {
        try {
            return ResponseEntity.ok(transactionService.getTransactionById(id).toString());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
        }
    }


    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request);
        return ResponseEntity.ok(transaction.toString());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTransactionById(@PathVariable UUID id, TransactionRequest request) {
        try {
            transactionService.updateTransactionById(id, request);
            return ResponseEntity.ok("Transaction with id " + id + " successfully updated");
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transaction not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable UUID id) {
        try {
            transactionService.deleteTransactionById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Transaction ID is not valid");
        }
    }
}
