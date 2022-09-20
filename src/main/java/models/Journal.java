package models;

import java.time.LocalDate;

public class Journal {
    private LocalDate date;
    private String title;
    private String content;
    private boolean starred;

    public Journal(LocalDate date, String title, String content) {
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

    public void write(String content) {
        this.content = content;
    }
}
