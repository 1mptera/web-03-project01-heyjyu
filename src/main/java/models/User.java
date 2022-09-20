package models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Journal> journals = new ArrayList<>();

    public void writeJournal(Journal journal) {
        journals.add(journal);
    }

    public List<Journal> journals() {
        return journals;
    }

    public void removeJournal(Journal journal) {
        journals.remove(journal);
    }
}
