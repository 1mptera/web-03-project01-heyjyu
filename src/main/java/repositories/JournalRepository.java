package repositories;

import models.Journal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JournalRepository {
    private List<Journal> journals;

    public JournalRepository() {
        journals = new ArrayList<>(loadJournals());
    }

    private List<Journal> loadJournals() {
        return List.of(); //TODO get from file
    }

    public void add(Journal journal) {
        journals.add(journal);
    }

    public Journal getById(UUID id) {
        Optional<Journal> element = journals.stream()
                .filter(journal -> journal.id().equals(id))
                .findFirst();

        if (element.isEmpty()) {
            return null;
        }

        return element.get();
    }

    public List<Journal> journals() {
        return journals;
    }

    public void removeById(UUID id) {
        journals.remove(getById(id));
    }

    public void updateById(UUID id, String content) {
        Journal journal = getById(id);

        journal.modify(content);
    }
}
