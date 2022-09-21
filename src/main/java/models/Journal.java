package models;

import java.time.LocalDate;
import java.util.UUID;

public class Journal {
    private UUID id;
    private LocalDate date;
    private String title;
    private String content;
    private boolean starred;

    public Journal(LocalDate date, String title, String content) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public Journal(LocalDate date, String title, String content, boolean starred) {
        this.date = date;
        this.title = title;
        this.content = content;
        this.starred = starred;
    }

    public LocalDate date() {
        return date;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }

    public boolean starred() {
        return starred;
    }

    public void toggleStar() {
        starred = !starred;
    }

    public void modify(String content) {
        this.content = content;
    }

    public UUID getId() {
        return id;
    }
}
