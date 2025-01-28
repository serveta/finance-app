package com.servet.finance_app.model;

import com.servet.finance_app.enums.Currency;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @NotBlank(message = "Balance is required")
    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotBlank(message = "Currency is required")
    private Currency currency;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // This will automatically set the creation date before persisting
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    // Custom constructor excluding 'id' field
    public Account(User user, BigDecimal balance, Currency currency) {
        this.user = user;
        this.balance = balance;
        this.currency = currency;
    }
}
