package repositories;

import models.Journal;
import models.Trading;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JournalRepositoryTest {

    @Test
    void add() {
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
    void remove() {
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
    void update() {
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
}
