package models;

import java.util.UUID;

public class Trading {
    private final UUID id;
    private String name;
    private String type;
    private Double unitPrice;
    private Double count;
    private double amount;

    public Trading(String name, String type, Double unitPrice, Double count) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
        this.unitPrice = unitPrice;
        this.count = count;
        this.amount = process();
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public Double unitPrice() {
        return unitPrice;
    }

    public double count() {
        return count;
    }

    public double amount() {
        return amount;
    }

    public double process() {
        return unitPrice * count;
    }

    @Override
    public String toString() {
        return name + " " + type + " " + amount + "원";
    }

    public UUID id() {
        return id;
    }
}
