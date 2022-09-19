package models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InvestGodTest {

    @Test
    void creation() {
        InvestGod investGod = new InvestGod();

        assertEquals(List.of(), investGod.journals());
    }

    @Test
    void write() {
        InvestGod investGod = new InvestGod();

        LocalDate date = LocalDate.of(2022, 9, 20);
        String title = "익절";
        String content = "지수가 하락하는 것을 보고 삼성전자 주식을 매도했다.";
        Journal journal = new Journal(date, title, content);

        investGod.writeJournal(journal);

        assertEquals(List.of(journal), investGod.journals());
    }

}
