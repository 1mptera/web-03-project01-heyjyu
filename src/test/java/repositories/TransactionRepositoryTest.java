package repositories;

import models.Asset;
import models.Transaction;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    @Test
    void add() {
        TransactionRepository transactionRepository = new TransactionRepository();

        String type = "입금";
        Double amount = 100000.0;
        String memo = "용돈";

        Transaction transaction = new Transaction(type, amount, memo);

        UUID id = transaction.id();

        transactionRepository.add(transaction);

        assertEquals(transaction, transactionRepository.getById(id));

        transactionRepository.removeById(id);
    }

    @Test
    void remove() {
        TransactionRepository transactionRepository = new TransactionRepository();

        String type = "입금";
        Double amount = 100000.0;
        String memo = "용돈";

        Transaction transaction = new Transaction(type, amount, memo);

        UUID id = transaction.id();

        transactionRepository.add(transaction);

        transactionRepository.removeById(id);

        assertNull(transactionRepository.getById(id));
    }
}
