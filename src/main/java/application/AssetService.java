package application;

import models.Asset;
import models.Trading;
import repositories.AssetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AssetService {
    private AssetRepository repository;

    public AssetService() {
        repository = AssetRepository.getInstance();
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
        return repository.assets().stream().filter(asset -> asset.count() > 0).toList();
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
}
