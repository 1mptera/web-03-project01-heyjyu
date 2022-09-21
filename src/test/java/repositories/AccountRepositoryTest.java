package repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    @Test
    void updateCash() {
        AccountRepository accountRepository = AccountRepository.getInstance();

        double amount = 10000.0;

        accountRepository.updateCash(amount);

        assertEquals(10000.0, accountRepository.cash());
    }
}
