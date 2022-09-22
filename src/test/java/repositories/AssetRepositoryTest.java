package repositories;

import models.Asset;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AssetRepositoryTest {

    @Test
    void add() throws IOException {
        AssetRepository assetRepository = new AssetRepository();

        String name = "삼성전자";
        Double averagePrice = 50000.0;
        Double count = 10.0;
        Double currentPrice = 56000.0;

        Asset asset = new Asset(name, averagePrice, count, currentPrice);

        UUID id = asset.id();

        assetRepository.add(asset);

        assertEquals(asset, assetRepository.getById(id));

        assetRepository.removeById(id);
    }

    @Test
    void remove() throws IOException {
        AssetRepository assetRepository = new AssetRepository();

        String name = "삼성전자";
        Double averagePrice = 50000.0;
        Double count = 10.0;
        Double currentPrice = 56000.0;

        Asset asset = new Asset(name, averagePrice, count, currentPrice);

        UUID id = asset.id();

        assetRepository.add(asset);

        assetRepository.removeById(id);

        assertNull(assetRepository.getById(id));
    }

    @Test
    void updateCurrentPrices() throws IOException {
        AssetRepository assetRepository = new AssetRepository();

        String name = "이런이름은 없지 주식";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        assetRepository.add(new Asset(name, averagePrice, count, currentUnitPrice));

        List<Asset> assets = assetRepository.assets();
        Asset asset = assets.get(assets.size() - 1);

        List<Double> prices = assetRepository.assets().stream().map(asset1 -> asset1.currentUnitPrice()).toList();

        List<Double> modifiedPrices = new ArrayList<>(prices);
        modifiedPrices.set(modifiedPrices.size() - 1, 50000.0);

        assetRepository.updateCurrentPrices(modifiedPrices);

        assertEquals(50000.0, assetRepository.getById(asset.id()).currentUnitPrice());

        assetRepository.removeById(asset.id());
    }
}
