package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TradingTest {

    @Test
    void creation() {
        String name = "비트코인";
        String type = "매수";
        Double unitPrice = 26585000.0;
        Double count = 0.01;

        Trading trading = new Trading(name, type, unitPrice, count);

        assertEquals("비트코인", trading.name());
        assertEquals("매수", trading.type());
        assertEquals(26585000.0, trading.unitPrice());
        assertEquals(0.01, trading.count());
    }

    @Test
    void string() {
        String name = "비트코인";
        String type = "매수";
        Double unitPrice = 26585000.0;
        Double count = 0.01;

        Trading trading = new Trading(name, type, unitPrice, count);

        assertEquals("비트코인 매수 265850.0원", trading.toString());
    }

    @Test
    void amount() {
        String name = "비트코인";
        String type = "매수";
        Double unitPrice = 26585000.0;
        Double count = 0.01;

        Trading trading = new Trading(name, type, unitPrice, count);

        assertEquals(265850.0, trading.amount());
    }
}
