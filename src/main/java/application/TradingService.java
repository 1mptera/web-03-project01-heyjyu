package application;

import models.Trading;

import java.util.ArrayList;
import java.util.List;

public class TradingService {
    private List<Trading> tradings = new ArrayList<>();

    public List<Trading> tradings() {
        return tradings;
    }

    public void add(String name, String type, Double unitPrice, Double count) {
        tradings.add(new Trading(name, type, unitPrice, count));
    }

    public List<String> tradingHistories() {
        return tradings.stream()
                .map(trading -> trading.toString())
                .toList();
    }
}
