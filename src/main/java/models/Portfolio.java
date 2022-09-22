package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Portfolio {
    private double cash;
    private List<Asset> assets;
    private int assetCount;

    public Portfolio(double cash, List<Asset> assets) {
        this.cash = cash;
        this.assets = new ArrayList<>(assets);
        this.assetCount = assets.size();
    }

    public HashMap<String, Double> assets(int count) {
        HashMap<String, Double> map = new HashMap<>();

        if (this.assetCount < count) {
            map.put("현금", cash);

            for (int i = 0; i < this.assetCount; i += 1) {
                map.put(assets.get(i).name(), assets.get(i).valuation());
            }

            return map;
        }

        if (this.assetCount > count) {
            this.assetCount = count;
        }

        Collections.sort(assets, Collections.reverseOrder());

        if (assets.get(this.assetCount - 1).valuation() > cash) {
            for (int i = 0; i < this.assetCount; i += 1) {
                map.put(assets.get(i).name(), assets.get(i).valuation());
            }

            return map;
        }

        map.put("현금", cash);

        for (int i = 0; i < this.assetCount - 1; i += 1) {
            map.put(assets.get(i).name(), assets.get(i).valuation());
        }

        return map;
    }
}
