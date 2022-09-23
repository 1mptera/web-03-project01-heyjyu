package models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TradingTest {

    @Test
    void creation() {
        String name = "비트코인";
        String type = "매수";
        Double unitPrice = 26585000.0;
        Double count = 0.01;
        UUID id = UUID.randomUUID();

        Trading trading1 = new Trading(name, type, unitPrice, count);
        Trading trading2 = new Trading(id, name, type, unitPrice, count);

        assertEquals("비트코인", trading1.name());
        assertEquals("매수", trading1.type());
        assertEquals(26585000.0, trading1.unitPrice());
        assertEquals(0.01, trading1.count());

        assertEquals(id, trading2.id());
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

    @Test
    void id() {
        String name = "비트코인";
        String type = "매수";
        Double unitPrice = 26585000.0;
        Double count = 0.01;

        Trading trading1 = new Trading(name, type, unitPrice, count);
        Trading trading2 = new Trading(name, type, unitPrice, count);

        assertNotEquals(trading1.id(), trading2.id());
    }

    @Test
    void csv() {
        String name = "비트코인";
        String type = "매수";
        Double unitPrice = 26585000.0;
        Double count = 0.01;

        Trading trading = new Trading(name, type, unitPrice, count);

        assertEquals(trading.id() + "|비트코인|매수|2.6585E7|0.01", trading.toCsvRow());
    }

    @Test
    void equals() {
        String name = "비트코인";
        String type = "매수";
        Double unitPrice = 26585000.0;
        Double count = 0.01;
        UUID id = UUID.randomUUID();

        Trading trading1 = new Trading(id, name, type, unitPrice, count);
        Trading trading2 = new Trading(id, name, type, unitPrice, count);

        assertEquals(trading1, trading2);
    }
}
