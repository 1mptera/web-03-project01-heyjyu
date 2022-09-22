package repositories;

import models.Goal;

public class GoalRepository {
    private Goal goal;

    public GoalRepository() {
        goal = new Goal(loadAmount());
    }

    private double loadAmount() {
        return 10000000;//TODO get from file
    }

    public double amount() {
        return goal.amount();
    }

    public void updateAmount(double amount) {
        goal.modifyAmount(amount);
    }
}
