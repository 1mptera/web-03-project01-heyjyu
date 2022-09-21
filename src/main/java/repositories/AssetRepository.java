package repositories;

import models.Asset;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AssetRepository {
    private static AssetRepository instance = new AssetRepository();

    private List<Asset> assets;

    private AssetRepository() {
        assets = new ArrayList<>(loadAssets());
    }

    private List<Asset> loadAssets() {
        return List.of(); //TODO get from file
    }

    public static AssetRepository getInstance() {
        return instance;
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
        return assets;
    }

    public void removeById(UUID id) {
        assets.remove(getById(id));
    }
}
