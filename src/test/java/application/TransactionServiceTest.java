package application;

import models.Transaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @Test
    void add() throws IOException {
        TransactionService transactionService = new TransactionService();

        int previousCount = transactionService.transactions().size();

        String type = "입금";
        Double amount = 10000.0;
        String memo = "용돈 받았당";

        transactionService.add(type, amount);
        transactionService.add(type, amount, memo);

        List<Transaction> transactions = transactionService.transactions();
        Transaction transaction1 = transactions.get(transactions.size() - 2);
        Transaction transaction2 = transactions.get(transactions.size() - 1);

        assertEquals(previousCount + 2, transactionService.transactions().size());

        transactionService.remove(transactionService.getId(transaction1));
        transactionService.remove(transactionService.getId(transaction2));
    }

    @Test
    void ids() throws IOException {
        TransactionService transactionService = new TransactionService();

        String type = "입금";
        Double amount = 10000.0;
        String memo = "용돈 받았당";

        transactionService.add(type, amount, memo);

        List<Transaction> transactions = transactionService.transactions();
        Transaction transaction = transactions.get(transactions.size() - 1);

        assertTrue(transactionService.getIds().contains(transactionService.getId(transaction)));

        transactionService.remove(transactionService.getId(transaction));
    }

    @Test
    void id() throws IOException {
        TransactionService transactionService = new TransactionService();

        String type = "입금";
        Double amount = 10000.0;
        String memo = "용돈 받았당";

        transactionService.add(type, amount, memo);

        List<Transaction> transactions = transactionService.transactions();
        Transaction transaction = transactions.get(transactions.size() - 1);

        assertEquals(transaction.id(), transactionService.getId(transaction));

        transactionService.remove(transactionService.getId(transaction));
    }

    @Test
    void remove() throws IOException {
        TransactionService transactionService = new TransactionService();

        String type = "입금";
        Double amount = 10000.0;
        String memo = "용돈 받았당";

        transactionService.add(type, amount, memo);

        List<Transaction> transactions = transactionService.transactions();
        Transaction transaction = transactions.get(transactions.size() - 1);

        transactionService.remove(transactionService.getId(transaction));

        assertFalse(transactionService.transactions().contains(transaction));
    }

    @Test
    void values() throws IOException {
        TransactionService transactionService = new TransactionService();

        String type = "입금";
        Double amount = 10000.0;
        String memo = "용돈 받았당";

        transactionService.add(type, amount, memo);

        List<Transaction> transactions = transactionService.transactions();
        Transaction transaction = transactions.get(transactions.size() - 1);

        assertEquals("입금", transactionService.type(transactionService.getId(transaction)));
        assertEquals(10000.0, transactionService.amount(transactionService.getId(transaction)));

        transactionService.remove(transactionService.getId(transaction));
    }
}
