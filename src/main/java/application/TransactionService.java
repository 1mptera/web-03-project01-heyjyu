package application;

import models.Transaction;
import repositories.TransactionRepository;

import java.util.List;
import java.util.UUID;

public class TransactionService {
    private TransactionRepository repository;

    public TransactionService() {
        repository = new TransactionRepository();
    }

    public void add(String type, Double amount) {
        repository.add(new Transaction(type, amount));
    }

    public void add(String type, Double amount, String memo) {
        repository.add(new Transaction(type, amount, memo));
    }

    public List<Transaction> transactions() {
        return repository.transactions();
    }

    public UUID getId(Transaction transaction) {
        return transaction.id();
    }

    public void remove(UUID id) {
        repository.removeById(id);
    }

    public List<UUID> getIds() {
        return repository.transactions()
                .stream()
                .map(transaction -> transaction.id())
                .toList();
    }
}
