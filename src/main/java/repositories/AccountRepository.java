package repositories;

import models.Account;

public class AccountRepository {
    private Account account;

    public AccountRepository() {
        account = new Account(loadCash());
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
