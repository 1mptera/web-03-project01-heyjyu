package application;

import models.Asset;
import repositories.AssetRepository;

import java.util.List;
import java.util.UUID;

public class AssetService {
    private AssetRepository repository;

    public AssetService() {
        repository = AssetRepository.getInstance();
    }

    public void add(String name, Double averagePrice, Double count, Double currentUnitPrice) {
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
}
