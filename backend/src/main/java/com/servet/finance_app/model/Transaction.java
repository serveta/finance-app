package com.servet.finance_app.model;

import com.servet.finance_app.enums.Currency;
import com.servet.finance_app.enums.TransactionType;
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
    @NotBlank(message = "Amount is required")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    @NotBlank(message = "Transaction type is required")
    private TransactionType transactionType;

    @Column(nullable = true)
    private String description; // For transfers

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    // Custom constructor excluding 'id', 'account' fields
    public Transaction(Account senderAccount, Account recipientAccount, BigDecimal amount, TransactionType transactionType, String description) {
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
        this. amount = amount;
        this.transactionType = transactionType;
        this.description = description;
    }

    // Custom constructor excluding 'id', 'senderAccount', 'recipientAccount' fields
    public Transaction(Account account, BigDecimal amount, TransactionType transactionType, String description) {
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
    }
}
