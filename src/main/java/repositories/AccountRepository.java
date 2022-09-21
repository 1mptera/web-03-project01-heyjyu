package repositories;

import models.Account;

public class AccountRepository {
    private static AccountRepository instance = new AccountRepository();

    private Account account;

    private AccountRepository() {
        account = new Account(loadCash());
    }

    public static AccountRepository getInstance() {
        return instance;
    }

    private double loadCash() {
        return 0; //TODO get from file
    }

    public double cash() {
        return account.cash();
    }

    public void updateCash(double amount) {
        account.modifyCash(amount);
    }
}
