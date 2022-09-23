package repositories;

import models.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AccountRepository {
    private Account account;

    public AccountRepository() throws FileNotFoundException {
        account = new Account(loadCash());
    }

    private double loadCash() throws FileNotFoundException {
        File file = new File("src/main/resources/DB/account.csv");

        Scanner scanner = new Scanner(file);

        return Double.parseDouble(scanner.nextLine());
    }

    public double cash() {
        return account.cash();
    }

    public void updateCash(double amount) throws IOException {
        account.modifyCash(amount);
        
        saveCash();
    }

    private void saveCash() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/DB/account.csv");

        fileWriter.write(String.valueOf(account.cash()));

        fileWriter.close();
    }
}
