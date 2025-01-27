package com.servet.finance_app.model;

import com.servet.finance_app.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = true) // nullable = true as default but it should be known
    private Account account; // For deposits/withdrawals

    @ManyToOne
    @JoinColumn(name = "sender_account_id", nullable = true)
    private Account senderAccount; // For transfers

    @ManyToOne
    @JoinColumn(name = "recipient_account_id", nullable = true)
    private Account recipientAccount; // For transfers

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    @Column(nullable = true)
    private String description; // For transfers

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
