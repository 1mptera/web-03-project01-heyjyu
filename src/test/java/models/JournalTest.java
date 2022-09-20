package models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JournalTest {

    @Test
    void creation() {
        LocalDate date = LocalDate.of(2022, 9, 19);
        String title = "익절";
        String content = "지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.";
        boolean starred = false;
        Journal journal1 = new Journal(date, title, content);
        Journal journal2 = new Journal(date, title, content, starred);

        assertEquals(LocalDate.of(2022, 9, 19), journal1.date());
        assertEquals("익절", journal1.title());
        assertEquals("지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.", journal1.content());

        assertEquals(LocalDate.of(2022, 9, 19), journal2.date());
        assertEquals("익절", journal2.title());
        assertEquals("지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.", journal2.content());
        assertEquals(false, journal2.starred());
    }

    @Test
    void toggleStar() {
        LocalDate date = LocalDate.of(2022, 9, 19);
        String title = "익절";
        String content = "지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.";
        boolean starred = true;
        Journal journal1 = new Journal(date, title, content);
        Journal journal2 = new Journal(date, title, content, starred);

        assertEquals(false, journal1.starred());
        assertEquals(true, journal2.starred());

        journal1.toggleStar();

        assertEquals(true, journal1.starred());

        journal1.toggleStar();

        assertEquals(false, journal1.starred());
    }

    @Test
    void write() {
        LocalDate date = LocalDate.of(2022, 9, 19);
        String title = "익절";
        String content = "";
        Journal journal = new Journal(date, title, content);

        assertEquals("", journal.content());

        journal.write("지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.");

        assertEquals("지수가 안 좋아지고 있어서 수익권에 있던 주식들을 모두 매도했다.", journal.content());
    }
}
