package models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void creation() {
        double cash = 10000.0;
        List<Asset> assets = List.of(
                new Asset("삼성전자", 30000.0, 10.0, 55000.0),
                new Asset("애플", 150000.0, 10.0, 200000.0)
        );

        User user = new User(cash, assets);

        assertEquals(2560000.0, user.totalAmount());
    }
}
