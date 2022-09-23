package repositories;

import models.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    public TransactionRepository() throws FileNotFoundException {
        loadTransactions();
    }

    private void loadTransactions() throws FileNotFoundException {
        File file = new File("src/main/resources/DB/transactions.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] words = parseTransaction(scanner.nextLine());
            transactions.add(new Transaction(UUID.fromString(words[0]), words[1], Double.parseDouble(words[2])));
        }
    }

    private String[] parseTransaction(String nextLine) {
        return nextLine.split(",");
    }

    public void add(Transaction transaction) throws IOException {
        transactions.add(transaction);

        appendToFile(transaction);
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

    public void removeById(UUID id) throws IOException {
        transactions.remove(getById(id));

        saveTransactions();
    }

    private void saveTransactions() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/DB/transactions.csv");

        for (Transaction transaction : transactions) {
            fileWriter.write(transaction.toCsvRow() + "\n");
        }

        fileWriter.close();
    }

    private void appendToFile(Transaction transaction) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/DB/transactions.csv", true);

        fileWriter.write(transaction.toCsvRow() + "\n");

        fileWriter.close();
    }
}
