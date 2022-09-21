package models;

import java.util.UUID;

public class Asset {
    private final UUID id;
    private String name;
    private Double averagePrice;
    private Double count;
    private Double currentUnitPrice;

    public Asset(String name, Double averagePrice, Double count, Double currentUnitPrice) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.averagePrice = averagePrice;
        this.count = count;
        this.currentUnitPrice = currentUnitPrice;
    }

    public UUID id() {
        return id;
    }
}
