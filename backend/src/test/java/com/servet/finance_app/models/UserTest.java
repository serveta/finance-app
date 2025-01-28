package com.servet.finance_app.models;

import com.servet.finance_app.enums.Role;
import com.servet.finance_app.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void testUserConstructorAndGetters() {
        // Arrange
        User user = new User("John", "Doe", "john.doe@example.com", "password123", Role.USER);

        // Act & Assert
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void testUserEqualsAndHashCode() {
        // Arrange
        User user1 = new User("Jane", "Doe", "jane.doe@example.com", "password123", Role.ADMIN);
        User user2 = new User("Jane", "Doe", "jane.doe@example.com", "password123", Role.ADMIN);

        // Act & Assert
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
