package application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoalServiceTest {

    @Test
    void modify() {
        GoalService goalService = new GoalService();

        double initialAmount = goalService.amount();

        goalService.modifyAmount(7654321);

        assertEquals(7654321, goalService.amount());

        goalService.modifyAmount(initialAmount);
    }
}
