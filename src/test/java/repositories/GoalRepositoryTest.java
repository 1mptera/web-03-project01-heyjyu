package repositories;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoalRepositoryTest {

    @Test
    void updateAmount() throws IOException {
        GoalRepository goalRepository = new GoalRepository();

        double initialAmount = goalRepository.amount();

        goalRepository.updateAmount(1234567);

        assertEquals(1234567, goalRepository.amount());

        goalRepository.updateAmount(initialAmount);
    }
}
