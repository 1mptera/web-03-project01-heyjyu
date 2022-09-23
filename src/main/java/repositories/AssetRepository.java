package repositories;

import models.Asset;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class AssetRepository {
    private List<Asset> assets = new ArrayList<>();

    public AssetRepository() throws FileNotFoundException {
        loadAssets();
    }

    private void loadAssets() throws FileNotFoundException {
        File file = new File("src/main/resources/DB/assets.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] words = parseAsset(scanner.nextLine());
            assets.add(new Asset(UUID.fromString(words[0]), words[1], Double.parseDouble(words[2]), Double.parseDouble(words[3]), Double.parseDouble(words[4])));
        }
    }

    private String[] parseAsset(String line) {
        return line.split(",");
    }

    public void add(Asset asset) throws IOException {
        assets.add(asset);

        appendToFile(asset);
    }

    public Asset getById(UUID id) {
        Optional<Asset> element = assets.stream()
                .filter(asset -> asset.id().equals(id))
                .findFirst();

        if (element.isEmpty()) {
            return null;
        }

        return element.get();
    }

    public List<Asset> assets() {
        return assets.stream().filter(asset -> asset.count() > 0).toList();
    }

    public void removeById(UUID id) throws IOException {
        assets.remove(getById(id));

        saveAssets();
    }

    public void updateCurrentPrices(List<Double> prices) throws IOException {
        if (assets.size() == 0) {
            return;
        }

        for (int i = 0; i < assets.size(); i += 1) {
            assets.get(i).modifyCurrentPrice(prices.get(i));
        }

        saveAssets();
    }

    private void saveAssets() throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/DB/assets.csv");

        for (Asset asset : assets) {
            fileWriter.write(asset.toCsvRow() + "\n");
        }

        fileWriter.close();
    }

    private void appendToFile(Asset asset) throws IOException {
        FileWriter fileWriter = new FileWriter("src/main/resources/DB/assets.csv", true);

        fileWriter.write(asset.toCsvRow() + "\n");

        fileWriter.close();
    }
}
