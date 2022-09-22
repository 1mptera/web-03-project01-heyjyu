package repositories;

import models.Asset;
import models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TransactionRepository {
    private List<Transaction> transactions;

    public TransactionRepository() {
        transactions = new ArrayList<>(loadTransactions());
    }

    private List<Transaction> loadTransactions() {
        return List.of(); //TODO get from file
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public Transaction getById(UUID id) {
        Optional<Transaction> element = transactions.stream()
                .filter(transaction -> transaction.id().equals(id))
                .findFirst();

        if (element.isEmpty()) {
            return null;
        }

        return element.get();
    }

    public List<Transaction> transactions() {
        return new ArrayList<>(transactions);
    }

    public void removeById(UUID id) {
        transactions.remove(getById(id));
    }
}
