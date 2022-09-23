package models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void creation() {
        String type = "입금";
        Double amount = 100000.0;
        String memo = "용돈";
        UUID id = UUID.randomUUID();

        Transaction transaction = new Transaction(type, amount, memo);

        assertEquals("입금", transaction.type());
        assertEquals(100000.0, transaction.amount());
        assertEquals("용돈", transaction.memo());

        transaction = new Transaction(type, amount);

        assertEquals("입금", transaction.type());
        assertEquals(100000.0, transaction.amount());
        assertEquals(null, transaction.memo());

        transaction = new Transaction(id, type, amount);

        assertEquals(id, transaction.id());
    }

    @Test
    void csv() {
        UUID id = UUID.randomUUID();
        String type = "입금";
        Double amount = 100000.0;

        Transaction transaction = new Transaction(id, type, amount);

        assertEquals(id + ",입금,100000.0", transaction.toCsvRow());
    }
}
