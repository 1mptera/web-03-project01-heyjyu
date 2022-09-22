package application;

import models.Asset;
import models.Transaction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @Test
    void add() {
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
    void ids() {
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
    void id() {
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
    void remove() {
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
}
