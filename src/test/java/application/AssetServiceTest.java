package application;

import models.Asset;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssetServiceTest {

    @Test
    void add() {
        AssetService assetService = new AssetService();

        int previousCount = assetService.assets().size();

        String name = "삼성전자";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        assetService.add(name, averagePrice, count, currentUnitPrice);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        assertEquals(previousCount + 1, assetService.assets().size());

        assetService.remove(assetService.getId(asset));
    }

    @Test
    void ids() {
        AssetService assetService = new AssetService();

        String name = "삼성전자";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        assetService.add(name, averagePrice, count, currentUnitPrice);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        assertTrue(assetService.getIds().contains(assetService.getId(asset)));

        assetService.remove(assetService.getId(asset));
    }

    @Test
    void id() {
        AssetService assetService = new AssetService();

        String name = "삼성전자";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        assetService.add(name, averagePrice, count, currentUnitPrice);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        assertEquals(asset.id(), assetService.getId(asset));

        assetService.remove(assetService.getId(asset));
    }

    @Test
    void remove() {
        AssetService assetService = new AssetService();

        String name = "삼성전자";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        assetService.add(name, averagePrice, count, currentUnitPrice);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        assetService.remove(assetService.getId(asset));

        assertFalse(assetService.assets().contains(asset));
    }
}
