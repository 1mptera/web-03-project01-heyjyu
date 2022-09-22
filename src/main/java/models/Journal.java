package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Journal {
    private UUID id;
    private LocalDate date;
    private String title;
    private String content;
    private boolean starred;
    private List<Trading> tradings;

    public Journal(LocalDate date, String title, String content, List<Trading> tradings) {
        this.id = UUID.randomUUID();
        this.date = date;
        this.title = title;
        this.content = content;
        this.tradings = new ArrayList<>(tradings);
    }

    public Journal(LocalDate date, String title, String content, List<Trading> tradings, boolean starred) {
        this(date, title, content, tradings);
        this.starred = starred;
    }

    public Journal(UUID id, LocalDate date, String title, String content, List<Trading> tradings, boolean starred) {
        this(date, title, content, tradings, starred);
        this.id = id;
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

    public UUID id() {
        return id;
    }

    public List<Trading> tradings() {
        return new ArrayList<>(tradings);
    }

    public String toCsvRow() {
        return id + "," + date + "," + title + "," + content + "," + String.join("/", tradings.stream().map(trading -> trading.toCsvRow()).toList()) + "," + starred;
    }
}
