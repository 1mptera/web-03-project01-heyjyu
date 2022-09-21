package application;

import models.User;

public class UserService {
    private AccountService accountService;
    private AssetService assetService;

    public UserService() {
        accountService = new AccountService();
        assetService = new AssetService();
    }

    public double totalAmount() {
        User user = new User(accountService.cash(), assetService.assets());

        return user.totalAmount();
    }
}
