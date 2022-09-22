package application;

import models.Portfolio;

import java.util.HashMap;

public class PortfolioService {
    private AccountService accountService;
    private AssetService assetService;

    public PortfolioService(AccountService accountService, AssetService assetService) {
        this.accountService = accountService;
        this.assetService = assetService;
    }

    public HashMap<String, Double> assets(int count) {
        Portfolio portfolio = new Portfolio(accountService.cash(), assetService.assets());

        return portfolio.assets(count);
    }
}
