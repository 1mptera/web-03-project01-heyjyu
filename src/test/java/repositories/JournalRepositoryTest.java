package repositories;

import models.Journal;
import models.Trading;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JournalRepositoryTest {

    @Test
    void add() throws IOException {
        JournalRepository journalRepository = new JournalRepository();

        LocalDate date = LocalDate.of(2022, 9, 21);
        String title = "익절";
        String content = "지수가 하락해서 매도했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));
        Journal journal = new Journal(date, title, content, tradings);

        UUID id = journal.id();

        journalRepository.add(journal);

        assertEquals(journal, journalRepository.getById(id));

        journalRepository.removeById(id);
    }

    @Test
    void remove() throws IOException {
        JournalRepository journalRepository = new JournalRepository();

        LocalDate date = LocalDate.of(2022, 9, 21);
        String title = "익절";
        String content = "지수가 하락해서 매도했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));
        Journal journal = new Journal(date, title, content, tradings);

        UUID id = journal.id();

        journalRepository.add(journal);

        journalRepository.removeById(id);

        assertNull(journalRepository.getById(id));
    }

    @Test
    void update() throws IOException {
        JournalRepository journalRepository = new JournalRepository();

        LocalDate date = LocalDate.of(2022, 9, 21);
        String title = "익절";
        String content = "지수가 하락해서 매도했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));
        Journal journal = new Journal(date, title, content, tradings);

        UUID id = journal.id();

        journalRepository.add(journal);

        journalRepository.updateById(id, "내용 수정했다.");

        assertEquals("내용 수정했다.", journalRepository.getById(id).content());

        journalRepository.removeById(id);
    }

    @Test
    void parseDate() throws FileNotFoundException {
        JournalRepository journalRepository = new JournalRepository();

        assertEquals(LocalDate.of(2022, 9, 21), journalRepository.parseLocalDate("2022-9-21"));
    }

    @Test
    void parseTrading() throws FileNotFoundException {
        JournalRepository journalRepository = new JournalRepository();

        String line = "45d45cf2-ea5d-450a-9cbf-d7aabce46e38|비트코인|매수|2.6585E7|0.01/36613bae-c1fc-43b0-ac18-3e7d2f81be83|비트코인|매도|2.6085E7|0.005";

        List<Trading> tradings = List.of(new Trading(UUID.fromString("45d45cf2-ea5d-450a-9cbf-d7aabce46e38"), "비트코인", "매수", 2.6585E7, 0.01),
                new Trading(UUID.fromString("36613bae-c1fc-43b0-ac18-3e7d2f81be83"), "비트코인", "매도", 2.6085E7, 0.005));

        assertEquals(tradings, journalRepository.parseTrading(line));
    }
}
