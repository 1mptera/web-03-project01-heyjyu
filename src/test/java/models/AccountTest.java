package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void creation() {
        Account account1 = new Account();

        assertEquals(0, account1.cash());

        double amount = 10000.0;
        Account account2 = new Account(amount);

        assertEquals(10000.0, account2.cash());
    }

    @Test
    void modifyCash() {
        Account account = new Account();

        double amount = 10000.0;

        account.modifyCash(amount);

        assertEquals(10000.0, account.cash());
    }
}
