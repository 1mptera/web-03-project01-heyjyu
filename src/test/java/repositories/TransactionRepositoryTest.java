package repositories;

import models.Asset;
import models.Transaction;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    @Test
    void add() throws IOException {
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
    void remove() throws IOException {
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
