package com.matin_devs.tally.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
@ToString
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer frequency;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany
    @JoinColumn(name = "expenses_id", nullable = false)
    private ArrayList<Expense> expenseList;

    @Builder
    public Budget(Integer frequency, User user) {
        this.frequency = frequency;
        this.user = user;
    }
}

