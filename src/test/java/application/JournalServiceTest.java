package application;

import models.Journal;
import models.Trading;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JournalServiceTest {

    @Test
    void write() throws IOException {
        JournalService journalService = new JournalService();

        int previousCount = journalService.journals().size();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매수", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        assertEquals(previousCount + 1, journalService.journals().size());

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void ids() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        assertTrue(journalService.getIds().contains(journalService.getId(journal)));

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void id() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        assertEquals(journal.id(), journalService.getId(journal));

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void starredId() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        assertFalse(journalService.getStarredIds().contains(journalService.getId(journal)));

        journalService.toggleStar(journalService.getId(journal));

        assertTrue(journalService.getStarredIds().contains(journalService.getId(journal)));

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void date() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        assertEquals(LocalDate.now(), journalService.date(journalService.getId(journal)));

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void title() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        assertEquals("삼성전자 매수", journalService.title(journalService.getId(journal)));

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void remove() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        journalService.remove(journalService.getId(journal));

        assertFalse(journalService.journals().contains(journal));
    }

    @Test
    void starred() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        assertEquals(false, journalService.starred(journalService.getId(journal)));

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void content() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        assertEquals("반도체 수요가 늘어날 것으로 예측되어 매수했다.", journalService.content(journalService.getId(journal)));

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void modify() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        journalService.modify(journalService.getId(journal), "내용 수정.");

        assertEquals("내용 수정.", journalService.content(journalService.getId(journal)));

        journalService.remove(journalService.getId(journal));
    }

    @Test
    void toggleStar() throws IOException {
        JournalService journalService = new JournalService();

        String title = "삼성전자 매수";
        String content = "반도체 수요가 늘어날 것으로 예측되어 매수했다.";
        List<Trading> tradings = List.of(new Trading("삼성전자", "매도", 56000.0, 10.0));

        journalService.writeJournal(title, content, tradings);

        List<Journal> journals = journalService.journals();
        Journal journal = journals.get(journals.size() - 1);

        journalService.toggleStar(journalService.getId(journal));

        assertEquals(true, journalService.starred(journalService.getId(journal)));

        journalService.remove(journalService.getId(journal));
    }
}
