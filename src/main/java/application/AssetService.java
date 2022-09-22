package application;

import models.Asset;
import models.Trading;
import repositories.AssetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AssetService {
    private AssetRepository repository;

    public AssetService() {
        repository = new AssetRepository();
    }

    public void add(String name, Double averagePrice, Double count, Double currentUnitPrice) {
        Optional<Asset> element = repository
                .assets()
                .stream()
                .filter(asset -> asset.name().equals(name))
                .findFirst();

        if (element.isPresent()) {
            element.get().process("매수", averagePrice, count, currentUnitPrice);

            return;
        }

        repository.add(new Asset(name, averagePrice, count, currentUnitPrice));
    }

    public List<Asset> assets() {
        return repository.assets();
    }

    public UUID getId(Asset asset) {
        return asset.id();
    }

    public void remove(UUID id) {
        repository.removeById(id);
    }

    public List<UUID> getIds() {
        return repository.assets()
                .stream()
                .map(asset -> asset.id())
                .toList();
    }

    public void process(List<Trading> tradings) {
        for (Trading trading : tradings) {
            Optional<Asset> element = repository.assets()
                    .stream()
                    .filter(asset -> asset.name().equals(trading.name()))
                    .findFirst();

            if (element.isPresent()) {
                element.get().process(trading.type(), trading.unitPrice(), trading.count(), trading.unitPrice());

                continue;
            }

            add(trading.name(), trading.unitPrice(), trading.count(), trading.unitPrice());
        }
    }

    public Double totalPurchase() {
        if (assets().size() == 0) {
            return 0.0;
        }

        return round(assets().stream()
                .map(asset -> asset.totalPurchase())
                .mapToDouble(value -> value)
                .sum());
    }

    public Double income() {
        return valuation() - totalPurchase();
    }

    public Double valuation() {
        return round(repository.assets().stream()
                .map(asset -> asset.valuation())
                .mapToDouble(value -> value)
                .sum());
    }

    public Double performance() {
        if (totalPurchase() == 0.0) {
            return 0.0;
        }

        return round(income() / totalPurchase() * 100);
    }

    public String name(UUID id) {
        return repository.getById(id).name();
    }

    public double count(UUID id) {
        return repository.getById(id).count();
    }

    public double currentUnitPrice(UUID id) {
        return repository.getById(id).currentUnitPrice();
    }

    public double round(double number) {
        return Math.round(number * 100) / 100.0;
    }

    public void updateCurrentPrices(List<Double> prices) {
        repository.updateCurrentPrices(prices);
    }

    public double averagePrice(UUID id) {
        return repository.getById(id).averagePrice();
    }

    public Double performance(UUID id) {
        return round(repository.getById(id).performance() * 100);
    }

    public Double income(UUID id) {
        return repository.getById(id).income();
    }

    public Double valuation(UUID id) {
        return repository.getById(id).valuation();
    }

    public Double totalPurchase(UUID id) {
        return repository.getById(id).totalPurchase();
    }
}
