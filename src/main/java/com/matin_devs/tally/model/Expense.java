package com.matin_devs.tally.model;

import com.matin_devs.tally.common.ExpenseCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
@NoArgsConstructor
@ToString
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private LocalDate date;

//    @ManyToOne
//    @JoinColumn(name = "budget_id", nullable = false)
//    private Budget budget;

    @Builder
    public Expense(String title, ExpenseCategory category, Float amount, LocalDate date/*, Budget budget*/) {
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.date = date;
//        this.budget = budget;
    }
}
