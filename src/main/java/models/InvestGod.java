package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvestGod {
    private List<Journal> journals = new ArrayList<>();

    public void loadJournals() {
//        journals = List.of(new Journal(new Date(), "익절", "내용")); //TODO 실제로 로드하기 파일에서
    }

    public void writeJournal(Journal journal) {
        journals.add(journal);
    }

    public List<Journal> journals() {
        return journals;
    }
}
