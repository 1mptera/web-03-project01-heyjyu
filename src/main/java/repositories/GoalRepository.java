package repositories;

import models.Goal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GoalRepository {
    private Goal goal;

    public GoalRepository() throws FileNotFoundException {
        goal = new Goal(loadAmount());
    }

    private double loadAmount() throws FileNotFoundException {
        File file = new File("src/main/resources/DB/goal.csv");

        Scanner scanner = new Scanner(file);

        return Double.parseDouble(scanner.nextLine());
    }

    public double amount() {
        return goal.amount();
    }

    public void updateAmount(double amount) throws IOException {
        goal.modifyAmount(amount);
        saveGoal();
    }

    private void saveGoal() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/DB/goal.csv");

        fileWriter.write(String.valueOf(goal.amount()));

        fileWriter.close();
    }
}
