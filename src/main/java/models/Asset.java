package models;

import java.util.List;
import java.util.UUID;

public class Asset implements Comparable<Asset> {
    private UUID id;
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

    public Asset(UUID id, String name, Double averagePrice, Double count, Double currentUnitPrice) {
        this(name, averagePrice, count, currentUnitPrice);
        this.id = id;
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

    public void modifyCurrentPrice(Double price) {
        currentUnitPrice = price;
    }

    @Override
    public int compareTo(Asset asset) {
        if (asset.valuation() < valuation()) {
            return 1;
        }

        if (asset.valuation() > valuation()) {
            return -1;
        }

        return 0;
    }

    public double income() {
        return valuation() - totalPurchase();
    }

    public double performance() {
        return (currentUnitPrice - averagePrice) / averagePrice;
    }

    public String toCsvRow() {
        return id + "," + name + "," + averagePrice + "," + count + "," + currentUnitPrice;
    }
}
