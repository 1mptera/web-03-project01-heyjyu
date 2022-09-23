package application;

import models.Transaction;
import repositories.TransactionRepository;

import javax.swing.Icon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private TransactionRepository repository;

    public TransactionService() throws FileNotFoundException {
        repository = new TransactionRepository();
    }

    public void add(String type, Double amount) throws IOException {
        repository.add(new Transaction(type, amount));
    }

    public void add(String type, Double amount, String memo) throws IOException {
        repository.add(new Transaction(type, amount, memo));
    }

    public List<Transaction> transactions() {
        return repository.transactions();
    }

    public UUID getId(Transaction transaction) {
        return transaction.id();
    }

    public void remove(UUID id) throws IOException {
        repository.removeById(id);
    }

    public List<UUID> getIds() {
        List<UUID> ids = new ArrayList<>();

        for (int i = 0; i < repository.transactions().size(); i += 1) {
            ids.add(repository.transactions()
                    .stream()
                    .map(transaction -> transaction.id())
                    .toList().get(repository.transactions().size() - 1 - i));
        }

        return ids;
    }

    public String type(UUID transactionId) {
        return repository.getById(transactionId).type();
    }

    public Double amount(UUID transactionId) {
        return repository.getById(transactionId).amount();
    }
}
