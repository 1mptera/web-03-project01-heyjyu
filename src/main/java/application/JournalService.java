package application;

import models.Journal;
import models.Trading;
import repositories.JournalRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class JournalService {
    private JournalRepository repository;

    public JournalService() throws FileNotFoundException {
        repository = new JournalRepository();
    }

    public List<Journal> journals() {
        return repository.journals();
    }

    public void writeJournal(String title, String content, List<Trading> tradings) throws IOException {
        repository.add(new Journal(LocalDate.now(), title, content, tradings));
    }

    public List<UUID> getIds() {
        return repository.journals()
                .stream()
                .map(journal -> journal.id())
                .toList();
    }

    public UUID getId(Journal journal) {
        return journal.id();
    }

    public LocalDate date(UUID journalId) {
        return repository.getById(journalId).date();
    }

    public String title(UUID journalId) {
        return repository.getById(journalId).title();
    }

    public void remove(UUID journalId) throws IOException {
        repository.removeById(journalId);
    }

    public boolean starred(UUID journalId) {
        return repository.getById(journalId).starred();
    }

    public String content(UUID journalId) {
        return repository.getById(journalId).content();
    }

    public void modify(UUID journalId, String content) throws IOException {
        repository.updateById(journalId, content);
    }

    public void toggleStar(UUID journalId) {
        repository.getById(journalId).toggleStar();
    }

    public List<UUID> getStarredIds() {
        return repository.journals()
                .stream()
                .filter(journal -> journal.starred())
                .map(journal -> journal.id())
                .toList();
    }
}
