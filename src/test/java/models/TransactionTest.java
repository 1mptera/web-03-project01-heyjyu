package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void creation() {
        String type = "입금";
        Double amount = 100000.0;
        String memo = "용돈";

        Transaction transaction = new Transaction(type, amount, memo);

        assertEquals("입금", transaction.type());
        assertEquals(100000.0, transaction.amount());
        assertEquals("용돈", transaction.memo());

        transaction = new Transaction(type, amount);

        assertEquals("입금", transaction.type());
        assertEquals(100000.0, transaction.amount());
        assertEquals(null, transaction.memo());
    }
}
