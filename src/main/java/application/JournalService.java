package application;

import models.Journal;
import repositories.JournalRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class JournalService {
    private JournalRepository repository;

    public JournalService() {
        repository = JournalRepository.getInstance();
    }

    public List<Journal> journals() {
        return repository.getJournals();
    }

    public void writeJournal(String title, String content) {
        repository.add(new Journal(LocalDate.now(), title, content));
    }

    public List<Object> getIds() {
        return Arrays.asList(repository.getJournals()
                .stream()
                .map(journal -> journal.getId())
                .toArray());
    }

    public UUID getId(Journal journal) {
        return journal.getId();
    }

    public LocalDate date(UUID journalId) {
        return repository.getById(journalId).date();
    }

    public String title(UUID journalId) {
        return repository.getById(journalId).title();
    }

    public void removeJournal(UUID journalId) {
        repository.removeById(journalId);
    }

    public boolean starred(UUID journalId) {
        return repository.getById(journalId).starred();
    }

    public String content(UUID journalId) {
        return repository.getById(journalId).content();
    }

    public void modify(UUID journalId, String content) {
        repository.getById(journalId).modify(content);
    }

    public void toggleStar(UUID journalId) {
        repository.getById(journalId).toggleStar();
    }
}
