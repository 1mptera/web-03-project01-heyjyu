package models;

public class Goal {
    private double amount;

    public Goal(double amount) {
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public void modifyAmount(double amount) {
        this.amount = amount;
    }
}
