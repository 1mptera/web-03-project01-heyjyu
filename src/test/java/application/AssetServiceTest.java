package application;

import models.Asset;
import models.Trading;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssetServiceTest {

    @Test
    void add() throws IOException {
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
    void ids() throws IOException {
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
    void id() throws IOException {
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
    void remove() throws IOException {
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
    void process() throws IOException {
        AssetService assetService = new AssetService();

        assetService.add("이런이름은 없지 주식1", 5000.0, 10.0, 6000.0);
        List<Asset> assets = assetService.assets();
        Asset asset1 = assets.get(assets.size() - 1);

        List<Trading> tradings = List.of(
                new Trading("이런이름은 없지 주식2", "매수", 55000.0, 10.0),
                new Trading("이런이름은 없지 주식1", "매수", 55000.0, 10.0),
                new Trading("이런이름은 없지 주식1", "매도", 56000.0, 20.0),
                new Trading("이런이름은 없지 주식2", "매도", 56000.0, 6.0),
                new Trading("이런이름은 없지 주식2", "매수", 54000.0, 16.0)
        );

        assetService.process(tradings);

        assets = assetService.assets();
        Asset asset2 = assets.get(assets.size() - 1);

        assertEquals("이런이름은 없지 주식2", asset2.name());
        assertEquals(20.0, asset2.count());
        assertEquals(53900.0, asset2.averagePrice());
        assertEquals(54000.0, asset2.currentUnitPrice());

        assetService.remove(assetService.getId(asset1));
        assetService.remove(assetService.getId(asset2));
    }

    @Test
    void updateCurrentPrices() throws IOException {
        AssetService assetService = new AssetService();

        String name = "이런이름은 없지 주식";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        assetService.add(name, averagePrice, count, currentUnitPrice);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        List<Double> prices = assetService.assets().stream().map(asset1 -> asset1.currentUnitPrice()).toList();

        List<Double> modifiedPrices = new ArrayList<>(prices);
        modifiedPrices.set(modifiedPrices.size() - 1, 50000.0);

        assetService.updateCurrentPrices(modifiedPrices);

        assertEquals(50000.0, assetService.currentUnitPrice(assetService.getId(asset)));

        assetService.remove(assetService.getId(asset));
    }

    @Test
    void totalPurchase() throws IOException {
        AssetService assetService = new AssetService();

        Double initialTotalPurchase = assetService.totalPurchase();

        assetService.add("이런이름은 없지 주식1", 5000.0, 10.0, 6000.0);
        List<Asset> assets = assetService.assets();
        Asset asset1 = assets.get(assets.size() - 1);

        List<Trading> tradings = List.of(
                new Trading("이런이름은 없지 주식2", "매수", 55000.0, 10.0),
                new Trading("이런이름은 없지 주식1", "매수", 55000.0, 10.0),
                new Trading("이런이름은 없지 주식1", "매도", 56000.0, 20.0),
                new Trading("이런이름은 없지 주식2", "매도", 56000.0, 6.0),
                new Trading("이런이름은 없지 주식2", "매수", 54000.0, 16.0)
        );

        assetService.process(tradings);

        assets = assetService.assets();
        Asset asset2 = assets.get(assets.size() - 1);

        Double totalPurchase = assetService.totalPurchase();

        assertEquals(55000.0 * 10.0 - 56000.0 * 6.0 + 54000.0 * 16.0, totalPurchase - initialTotalPurchase);

        assetService.remove(assetService.getId(asset1));
        assetService.remove(assetService.getId(asset2));
    }

    @Test
    void round() throws FileNotFoundException {
        AssetService assetService = new AssetService();

        assertEquals(18.22, assetService.round(18.2223445));
    }

    @Test
    void values() throws IOException {
        AssetService assetService = new AssetService();

        String name = "이런이름은 없지 주식";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 57000.0;

        assetService.add(name, averagePrice, count, currentUnitPrice);

        List<Asset> assets = assetService.assets();
        Asset asset = assets.get(assets.size() - 1);

        assertEquals(56000.0, assetService.averagePrice(assetService.getId(asset)));
        assertEquals(570000.0, assetService.valuation(assetService.getId(asset)));
        assertEquals(560000.0, assetService.totalPurchase(assetService.getId(asset)));
        assertEquals(10.0, assetService.count(assetService.getId(asset)));
        assertEquals(10000.0, assetService.income(assetService.getId(asset)));
        assertEquals(1.79, assetService.performance(assetService.getId(asset)));

        assetService.remove(assetService.getId(asset));
    }
}
