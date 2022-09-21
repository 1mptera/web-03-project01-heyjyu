package application;

import models.Asset;
import models.Trading;
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

        String name = "이런이름은 없지 주식";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        assetService.add(name, averagePrice, count, currentUnitPrice);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        assertEquals(previousCount + 1, assetService.assets().size());

        assetService.add(name, averagePrice, count, currentUnitPrice);
        assetService.add(name, averagePrice, count, currentUnitPrice);

        assertEquals(previousCount + 1, assetService.assets().size());

        assetService.remove(assetService.getId(asset));
    }

    @Test
    void ids() {
        AssetService assetService = new AssetService();

        String name = "이런이름은 없지 주식";
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

        String name = "이런이름은 없지 주식";
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

        String name = "이런이름은 없지 주식";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        assetService.add(name, averagePrice, count, currentUnitPrice);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        assetService.remove(assetService.getId(asset));

        assertFalse(assetService.assets().contains(asset));
    }

    @Test
    void process() {
        AssetService assetService = new AssetService();

        List<Trading> tradings = List.of(
                new Trading("이런이름은 없지 주식1", "매수", 55000.0, 10.0),
                new Trading("이런이름은 없지 주식2", "매수", 55000.0, 10.0),
                new Trading("이런이름은 없지 주식2", "매도", 56000.0, 10.0),
                new Trading("이런이름은 없지 주식1", "매도", 56000.0, 6.0),
                new Trading("이런이름은 없지 주식1", "매수", 54000.0, 16.0)
        );

        assetService.process(tradings);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        assertEquals("이런이름은 없지 주식1", asset.name());
        assertEquals(20.0, asset.count());
        assertEquals(53900.0, asset.averagePrice());
        assertEquals(54000.0, asset.currentUnitPrice());
        
        assetService.remove(assetService.getId(asset));
    }
}
