package application;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Test
    void modifyCash() throws IOException {
        AccountService accountService = new AccountService();

        Double initialAmount = accountService.cash();

        accountService.modifyCash(100.0);

        assertEquals(100.0, accountService.cash());

        accountService.modifyCash(initialAmount);
    }

    @Test
    void process() throws IOException {
        AccountService accountService = new AccountService();

        String type = "입금";
        Double amount = 10000.0;

        Double initialAmount = accountService.cash();

        accountService.process(type, amount);

        assertEquals(10000.0, accountService.cash() - initialAmount);

        accountService.process("출금", 10000.0);

        assertEquals(initialAmount, accountService.cash());
    }
}
