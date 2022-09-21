package application;

import models.Asset;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void totalAmount() {
        UserService userService = new UserService();

        Double initialTotalAmount = userService.totalAmount();
        System.out.println(initialTotalAmount);

        AssetService assetService = new AssetService();
        assetService.add("이런 이름을 가진 자산은 없지", 5000000.0, 1.0, 10000000.0);
        Asset asset = assetService.assets().get(0);

        Double totalAmount = userService.totalAmount();

        assertEquals(10000000.0, totalAmount - initialTotalAmount);

        assetService.remove(assetService.getId(asset));
    }
}
