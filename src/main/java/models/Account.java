package models;

public class Account {
    private double amount;

    public Account(double amount) {
        this.amount = amount;
    }

    public Account() {
        this.amount = 0;
    }

    public double cash() {
        return amount;
    }

    public void modifyCash(double amount) {
        this.amount = amount;
    }
}
