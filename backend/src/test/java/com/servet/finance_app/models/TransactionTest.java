package com.servet.finance_app.models;

import com.servet.finance_app.enums.Currency;
import com.servet.finance_app.enums.Role;
import com.servet.finance_app.enums.TransactionType;
import com.servet.finance_app.model.Account;
import com.servet.finance_app.model.Transaction;
import com.servet.finance_app.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    @Test
    void testTransactionConstructorAndGetters() {
        // Arrange
        User user = new User("John", "Doe", "john.doe@example.com", "password123", Role.USER);
        Account account = new Account(user, new BigDecimal("1000.00"), Currency.USD);
        Transaction transaction = new Transaction(account, new BigDecimal("200.00"), TransactionType.DEPOSIT, "Deposit description");

        Account senderAccount = new Account(user, new BigDecimal("1000.00"), Currency.USD);
        Account recipientAccount = new Account(user, new BigDecimal("500.00"), Currency.USD);
        Transaction transaction2 = new Transaction(senderAccount, recipientAccount, new BigDecimal("200.00"), TransactionType.TRANSFER, "Transfer description");

        // Act & Assert
        assertEquals(account, transaction.getAccount());
        assertEquals(new BigDecimal("200.00"), transaction.getAmount());
        assertEquals(TransactionType.DEPOSIT, transaction.getTransactionType());
        assertEquals("Deposit description", transaction.getDescription());

        assertEquals(senderAccount, transaction2.getSenderAccount());
        assertEquals(recipientAccount, transaction2.getRecipientAccount());
        assertEquals(new BigDecimal("200.00"), transaction2.getAmount());
        assertEquals(TransactionType.TRANSFER, transaction2.getTransactionType());
        assertEquals("Transfer description", transaction2.getDescription());
    }

    @Test
    void testTransactionEqualsAndHashCode() {
        // Arrange
        User user = new User("John", "Doe", "john.doe@example.com", "password123", Role.USER);
        Account account = new Account(user, new BigDecimal("1000.00"), Currency.USD);
        Transaction transaction1 = new Transaction(account, new BigDecimal("200.00"), TransactionType.WITHDRAWAL, "Withdrawal description");
        Transaction transaction2 = new Transaction(account, new BigDecimal("200.00"), TransactionType.WITHDRAWAL, "Withdrawal description");

        // Act & Assert
        assertEquals(transaction1, transaction2);
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }
}
