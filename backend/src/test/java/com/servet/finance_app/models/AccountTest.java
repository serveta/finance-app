package com.servet.finance_app.models;

import com.servet.finance_app.enums.Role;
import com.servet.finance_app.model.Account;
import com.servet.finance_app.enums.Currency;
import com.servet.finance_app.model.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    @Test
    void testAccountConstructorAndGetters() {
        // Arrange
        User user = new User("John", "Doe", "john.doe@example.com", "password123", Role.USER);
        Account account = new Account(user, new BigDecimal("1000.00"), Currency.USD);

        // Act & Assert
        assertEquals(user, account.getUser());
        assertEquals(new BigDecimal("1000.00"), account.getBalance());
        assertEquals(Currency.USD, account.getCurrency());
    }

    @Test
    void testAccountEqualsAndHashCode() {
        // Arrange
        User user = new User("John", "Doe", "john.doe@example.com", "password123", Role.USER);
        Account account1 = new Account(user, new BigDecimal("1000.00"), Currency.USD);
        Account account2 = new Account(user, new BigDecimal("1000.00"), Currency.USD);

        // Act & Assert
        assertEquals(account1, account2);
        assertEquals(account1.hashCode(), account2.hashCode());
    }
}
