package models;

import java.util.List;

public class User {
    private double cash;
    private List<Asset> assets;

    public User(double cash, List<Asset> assets) {
        this.cash = cash;
        this.assets = assets;
    }

    public double totalAmount() {
        return cash + assets.stream()
                .map(asset -> asset.valuation())
                .mapToDouble(value -> value)
                .sum();
    }
}
