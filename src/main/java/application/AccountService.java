package application;

import repositories.AccountRepository;

public class AccountService {
    private AccountRepository repository;

    public AccountService() {
        repository = new AccountRepository();
    }

    public void modifyCash(Double amount) {
        repository.updateCash(amount);
    }

    public double cash() {
        return repository.cash();
    }
}
