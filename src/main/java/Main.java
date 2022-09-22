import application.AccountService;
import application.AssetService;
import application.GoalService;
import application.JournalService;
import application.PortfolioService;
import application.TradingService;
import application.UserService;
import frames.MainFrame;

import javax.swing.JFrame;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Main application = new Main();

        application.run();
    }

    private void run() throws IOException {
        JournalService journalService = new JournalService();
        TradingService tradingService = new TradingService();
        AccountService accountService = new AccountService();
        AssetService assetService = new AssetService();
        UserService userService = new UserService(accountService, assetService);
        PortfolioService portfolioService = new PortfolioService(accountService, assetService);
        GoalService goalService = new GoalService();

        JFrame mainFrame = new MainFrame(journalService, tradingService, accountService, userService, assetService, portfolioService, goalService);

        mainFrame.setVisible(true);
    }
}
