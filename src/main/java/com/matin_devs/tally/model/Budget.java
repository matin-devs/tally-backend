package com.matin_devs.tally.model;

import com.matin_devs.tally.common.TimePeriod;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
@ToString
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private TimePeriod frequency;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection
    @CollectionTable(
            name = "budget_category",
            joinColumns = @JoinColumn(name = "budget_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"budget_id", "category_id"})
    )
    @MapKeyJoinColumn(name = "category_id")
    @Column(name = "amount")
    private Map<TransactionCategory, BigDecimal> categoryBudgets = new HashMap<>();

    @Builder
    public Budget(TimePeriod frequency, User user) {
        this.frequency = frequency;
        this.user = user;
    }
}

