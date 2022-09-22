package application;

import repositories.AccountRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AccountService {
    private AccountRepository repository;

    public AccountService() throws FileNotFoundException {
        repository = new AccountRepository();
    }

    public void modifyCash(Double amount) throws IOException {
        repository.updateCash(amount);
    }

    public double cash() {
        return repository.cash();
    }

    public void process(String type, Double amount) throws IOException {
        if (type.equals("입금")) {
            repository.updateCash(repository.cash() + amount);
        }

        if (type.equals("출금")) {
            repository.updateCash(repository.cash() - amount);
        }
    }
}
