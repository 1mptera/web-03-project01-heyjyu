package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoalTest {

    @Test
    void creation() {
        Goal goal = new Goal(10000000);

        assertEquals(10000000, goal.amount());
    }

    @Test
    void modifyAmount() {
        Goal goal = new Goal(10000000);

        goal.modifyAmount(10000);
        
        assertEquals(10000, goal.amount());
    }
}
