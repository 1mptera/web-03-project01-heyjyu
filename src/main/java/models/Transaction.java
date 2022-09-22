package models;

import java.util.UUID;

public class Transaction {
    private final UUID id;
    private String type;
    private Double amount;
    private String memo;

    public Transaction(String type, Double amount) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.amount = amount;
    }

    public Transaction(String type, Double amount, String memo) {
        this(type, amount);
        this.memo = memo;
    }

    public UUID id() {
        return id;
    }

    public String type() {
        return type;
    }

    public Double amount() {
        return amount;
    }

    public String memo() {
        return memo;
    }
}
