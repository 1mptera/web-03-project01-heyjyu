package models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void creation() {
        User user = new User();

        assertEquals(List.of(), user.journals());
    }

    @Test
    void write() {
        User user = new User();

        LocalDate date = LocalDate.of(2022, 9, 20);
        String title = "익절";
        String content = "지수가 하락하는 것을 보고 삼성전자 주식을 매도했다.";
        Journal journal = new Journal(date, title, content);

        user.writeJournal(journal);

        assertEquals(List.of(journal), user.journals());
    }

    @Test
    void removeJournal() {
        User user = new User();

        LocalDate date = LocalDate.of(2022, 9, 20);
        String title = "익절";
        String content = "지수가 하락하는 것을 보고 삼성전자 주식을 매도했다.";
        Journal journal = new Journal(date, title, content);

        user.writeJournal(journal);
        user.removeJournal(journal);

        assertEquals(List.of(), user.journals());
    }
}
