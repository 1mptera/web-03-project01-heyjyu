package repositories;

import models.Asset;
import models.Journal;
import models.Trading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class JournalRepository {
    private List<Journal> journals = new ArrayList<>();

    public JournalRepository() throws FileNotFoundException {
        loadJournals();
    }

    private void loadJournals() throws FileNotFoundException {
        File file = new File("src/main/resources/DB/journals.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] words = parseJournal(scanner.nextLine());
            journals.add(new Journal(UUID.fromString(words[0]), parseLocalDate(words[1]), words[2], words[3], parseTrading(words[4]), Boolean.getBoolean(words[5])));
        }
    }

    public LocalDate parseLocalDate(String line) {
        String[] words = line.split("-");

        return LocalDate.of(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]));
    }

    public String[] parseJournal(String line) {
        return line.split(",");
    }

    public List<Trading> parseTrading(String line) {
        List<Trading> tradings = new ArrayList<>();

        String[] words = line.split("/");

        for (String word : words) {
            String[] elements = word.split("\\|");

            tradings.add(new Trading(UUID.fromString(elements[0]), elements[1], elements[2], Double.parseDouble(elements[3]), Double.parseDouble(elements[4])));
        }

        return tradings;
    }

    public void add(Journal journal) throws IOException {
        journals.add(journal);

        appendToFile(journal);
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

    public void removeById(UUID id) throws IOException {
        journals.remove(getById(id));

        saveJournals();
    }

    public void updateById(UUID id, String content) throws IOException {
        Journal journal = getById(id);

        journal.modify(content);

        saveJournals();
    }

    private void saveJournals() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/DB/journals.csv");

        for (Journal journal : journals) {
            fileWriter.write(journal.toCsvRow() + "\n");
        }

        fileWriter.close();
    }

    private void appendToFile(Journal journal) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/DB/journals.csv", true);

        fileWriter.write(journal.toCsvRow() + "\n");

        fileWriter.close();
    }
}
