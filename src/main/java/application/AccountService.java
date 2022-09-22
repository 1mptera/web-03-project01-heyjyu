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

    public void process(String type, Double amount) {
        if (type.equals("입금")) {
            repository.updateCash(repository.cash() + amount);
        }

        if (type.equals("출금")) {
            repository.updateCash(repository.cash() - amount);
        }
    }
}
