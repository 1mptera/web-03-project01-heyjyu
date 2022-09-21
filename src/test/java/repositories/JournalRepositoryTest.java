package repositories;

import models.Journal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JournalRepositoryTest {

    @Test
    void add() {
        JournalRepository journalRepository = JournalRepository.getInstance();

        LocalDate date = LocalDate.of(2022, 9, 21);
        String title = "익절";
        String content = "지수가 하락해서 매도했다.";
        Journal journal = new Journal(date, title, content);

        UUID id = journal.getId();

        journalRepository.add(journal);

        assertEquals(journal, journalRepository.getById(id));
    }

    @Test
    void remove() {
        JournalRepository journalRepository = JournalRepository.getInstance();

        LocalDate date = LocalDate.of(2022, 9, 21);
        String title = "익절";
        String content = "지수가 하락해서 매도했다.";
        Journal journal = new Journal(date, title, content);

        UUID id = journal.getId();

        journalRepository.add(journal);

        journalRepository.removeById(id);

        assertEquals(List.of(), journalRepository.getJournals());
    }

    @Test
    void update() {
        JournalRepository journalRepository = JournalRepository.getInstance();

        LocalDate date = LocalDate.of(2022, 9, 21);
        String title = "익절";
        String content = "지수가 하락해서 매도했다.";
        Journal journal = new Journal(date, title, content);

        UUID id = journal.getId();

        journalRepository.add(journal);

        journalRepository.updateById(id, "내용 수정했다.");

        assertEquals("내용 수정했다.", journalRepository.getById(id).content());
    }
}
