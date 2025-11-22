package com.matin_devs.tally.repository;

import com.matin_devs.tally.model.Expense;
import com.matin_devs.tally.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<User> findById(String username);
    boolean existsById(String username);
}