package application;

import models.Trading;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TradingServiceTest {

    @Test
    void tradings() {
        TradingService tradingService = new TradingService();

        assertEquals(List.of(), tradingService.tradings());
    }

    @Test
    void add() {
        TradingService tradingService = new TradingService();

        String name = "삼성전자";
        String type = "매도";
        Double unitPrice = 56000.0;
        Double count = 10.0;

        tradingService.add(name, type, unitPrice, count);

        assertEquals("삼성전자", tradingService.tradings().get(0).name());
        assertEquals("매도", tradingService.tradings().get(0).type());
        assertEquals(56000.0, tradingService.tradings().get(0).unitPrice());
        assertEquals(10.0, tradingService.tradings().get(0).count());
    }

    @Test
    void tradingHistories() {
        TradingService tradingService = new TradingService();

        String name = "삼성전자";
        String type = "매도";
        Double unitPrice = 56000.0;
        Double count = 10.0;

        tradingService.add(name, type, unitPrice, count);
        assertEquals("삼성전자 매도 560000.0원", tradingService.tradingHistories().get(0));
    }
}
