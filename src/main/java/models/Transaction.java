package models;

import java.util.UUID;

public class Transaction {
    private UUID id;
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

    public Transaction(UUID id, String type, Double amount) {
        this(type, amount);
        this.id = id;
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

    public String toCsvRow() {
        return id + "," + type + "," + amount;
    }
}
