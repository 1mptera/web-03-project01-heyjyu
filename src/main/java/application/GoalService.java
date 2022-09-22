package application;

import repositories.GoalRepository;

public class GoalService {
    private GoalRepository repository;

    public GoalService() {
        repository = new GoalRepository();
    }

    public double amount() {
        return repository.amount();
    }

    public void modifyAmount(double amount) {
        repository.updateAmount(amount);
    }
}
