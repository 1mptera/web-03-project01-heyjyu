package application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void modifyCash() {
        AccountService accountService = new AccountService();

        accountService.modifyCash(100.0);

        assertEquals(100.0, accountService.cash());
    }
}
