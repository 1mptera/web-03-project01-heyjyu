package application;

import repositories.GoalRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GoalService {
    private GoalRepository repository;

    public GoalService() throws FileNotFoundException {
        repository = new GoalRepository();
    }

    public double amount() {
        return repository.amount();
    }

    public void modifyAmount(double amount) throws IOException {
        repository.updateAmount(amount);
    }
}
