package models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class JournalTest {

    @Test
    void creation() {
        LocalDate date = LocalDate.of(2022, 9, 19);
        String title = "익절";
        String content = "지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));
        boolean starred = false;
        Journal journal1 = new Journal(date, title, content, tradings);
        Journal journal2 = new Journal(date, title, content, tradings, starred);

        assertEquals(LocalDate.of(2022, 9, 19), journal1.date());
        assertEquals("익절", journal1.title());
        assertEquals("지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.", journal1.content());
        assertEquals(tradings, journal1.tradings());

        assertEquals(LocalDate.of(2022, 9, 19), journal2.date());
        assertEquals("익절", journal2.title());
        assertEquals("지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.", journal2.content());
        assertEquals(false, journal2.starred());
        assertEquals(tradings, journal2.tradings());
    }

    @Test
    void toggleStar() {
        LocalDate date = LocalDate.of(2022, 9, 19);
        String title = "익절";
        String content = "지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));
        boolean starred = true;
        Journal journal1 = new Journal(date, title, content, tradings);
        Journal journal2 = new Journal(date, title, content, tradings, starred);

        assertEquals(false, journal1.starred());
        assertEquals(true, journal2.starred());

        journal1.toggleStar();

        assertEquals(true, journal1.starred());

        journal1.toggleStar();

        assertEquals(false, journal1.starred());
    }

    @Test
    void modify() {
        LocalDate date = LocalDate.of(2022, 9, 19);
        String title = "익절";
        String content = "";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));
        Journal journal = new Journal(date, title, content, tradings);

        assertEquals("", journal.content());

        journal.modify("지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.");

        assertEquals("지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.", journal.content());
    }

    @Test
    void id() {
        LocalDate date = LocalDate.of(2022, 9, 19);
        String title = "익절";
        String content = "지수가 안좋아지고 있어서 매도했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));
        Journal journal1 = new Journal(date, title, content, tradings);
        Journal journal2 = new Journal(date, title, content, tradings);

        assertNotEquals(journal1.id(), journal2.id());
    }
}
