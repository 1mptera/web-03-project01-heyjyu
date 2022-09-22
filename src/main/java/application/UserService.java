package application;

import models.User;

public class UserService {
    private AccountService accountService;
    private AssetService assetService;

    public UserService(AccountService accountService, AssetService assetService) {
        this.accountService = accountService;
        this.assetService = assetService;
    }

    public double totalAmount() {
        User user = new User(accountService.cash(), assetService.assets());

        return user.totalAmount();
    }

    public AssetService assetService() {
        return assetService;
    }
}
