package models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioTest {

    @Test
    void creation() {
        double cash = 100000.0;
        List<Asset> assets = List.of(new Asset("삼성전자", 35000.0, 10.0, 55000.0));

        Portfolio portfolio = new Portfolio(cash, assets);

        assertEquals(2, portfolio.assets(5).size());
        assertEquals(2, portfolio.assets(3).size());
        assertEquals(1, portfolio.assets(1).size());

        assertEquals(550000.0, portfolio.assets(1).get("삼성전자"));
        assertEquals(100000.0, portfolio.assets(2).get("현금"));
    }
}
