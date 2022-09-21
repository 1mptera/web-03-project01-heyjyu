package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssetTest {

    @Test
    void creation() {
        String name = "삼성전자";
        Double averagePrice = 56000.0;
        Double count = 10.0;
        Double currentUnitPrice = 56000.0;

        Asset asset1 = new Asset(name, averagePrice, count, currentUnitPrice);
        Asset asset2 = new Asset(name, averagePrice, count, currentUnitPrice);

        assertNotEquals(asset1.id(), asset2.id());
    }
}
