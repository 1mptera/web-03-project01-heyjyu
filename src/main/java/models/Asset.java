package models;

import java.util.UUID;

public class Asset {
    private final UUID id;
    private String name;
    private Double averagePrice;
    private Double count;
    private Double currentUnitPrice;
    private Double amount;

    public Asset(String name, Double averagePrice, Double count, Double currentUnitPrice) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.averagePrice = averagePrice;
        this.count = count;
        this.currentUnitPrice = currentUnitPrice;
        this.amount = averagePrice * count;
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public double averagePrice() {
        return averagePrice;
    }

    public double count() {
        return count;
    }

    public double currentUnitPrice() {
        return currentUnitPrice;
    }

    public void process(String type, Double averagePrice, Double count, Double currentUnitPrice) {
        this.currentUnitPrice = currentUnitPrice;

        if (type.equals("매수")) {
            double amount = this.averagePrice * this.count + averagePrice * count;

            this.count += count;

            this.averagePrice = amount / this.count;
        }

        if (type.equals("매도")) {
            double amount = this.averagePrice * this.count - averagePrice * count;

            this.count -= count;

            this.averagePrice = amount / this.count;
        }
    }

    public Double valuation() {
        return currentUnitPrice * count;
    }

    public Double totalPurchase() {
        return averagePrice * count;
    }
}
