package repositories;

import models.Asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AssetRepository {
    private List<Asset> assets;

    public AssetRepository() {
        assets = new ArrayList<>(loadAssets());
    }

    private List<Asset> loadAssets() {
        return List.of(); //TODO get from file
    }

    public void add(Asset journal) {
        assets.add(journal);
    }

    public Asset getById(UUID id) {
        Optional<Asset> element = assets.stream()
                .filter(asset -> asset.id().equals(id))
                .findFirst();

        if (element.isEmpty()) {
            return null;
        }

        return element.get();
    }

    public List<Asset> assets() {
        return assets.stream().filter(asset -> asset.count() > 0).toList();
    }

    public void removeById(UUID id) {
        assets.remove(getById(id));
    }

    public void updateCurrentPrices(List<Double> prices) {
        if (assets.size() == 0) {
            return;
        }

        for (int i = 0; i < assets.size(); i += 1) {
            assets.get(i).modifyCurrentPrice(prices.get(i));
        }
    }
}
