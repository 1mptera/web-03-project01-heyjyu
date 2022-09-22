package panels;

import application.AccountService;
import application.AssetService;
import application.GoalService;
import application.JournalService;
import application.PortfolioService;
import application.TradingService;
import application.TransactionService;
import application.UserService;
import models.Resources;
import themes.Colors;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

public class NavigatorPanel extends JPanel {
    private JournalService journalService;
    private AssetService assetService;
    private AccountService accountService;
    private UserService userService;
    private PortfolioService portfolioService;
    private GoalService goalService;
    private TransactionService transactionService;

    private JPanel contentPanel;

    public NavigatorPanel(JPanel contentPanel, JournalService journalService, AccountService accountService,
                          UserService userService, AssetService assetService,
                          PortfolioService portfolioService, GoalService goalService, TransactionService transactionService) throws IOException {
        this.contentPanel = contentPanel;

        this.journalService = journalService;
        this.accountService = accountService;
        this.userService = userService;
        this.assetService = assetService;
        this.portfolioService = portfolioService;
        this.goalService = goalService;
        this.transactionService = transactionService;

        setPreferredSize(new Dimension(400, 70));
        setLayout(new GridLayout(1, 5));
        setBackground(Colors.PRIMARY);

        initJournalButton();
        initBookmarkButton();
        initMyAssetsButton();
//        initWishListButton();
        initGoalButton();
    }

    private void initJournalButton() {
        Image image = Resources.FILE_IMAGE;
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("일지", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            try {
                updateContentPanel(new panels.JournalsPanel(journalService, assetService));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        add(button);
    }

    private void initBookmarkButton() {
        Image image = Resources.STAR_IMAGE;
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("즐겨찾기", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            try {
                updateContentPanel(new panels.BookmarkPanel(journalService));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        add(button);
    }

    private void initMyAssetsButton() {
        Image image = Resources.BANKNOTE_IMAGE;
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("나의 자산", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            updateContentPanel(new panels.MyAssetsPanel(accountService, userService, assetService, portfolioService, transactionService));
        });

        add(button);
    }

    private void initWishListButton() {
        Image image = Resources.HEART_IMAGE;
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("관심", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            updateContentPanel(new panels.WishListPanel());
        });

        add(button);
    }

    private void initGoalButton() {
        Image image = Resources.MEDAL_IMAGE;
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("목표", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            updateContentPanel(new panels.GoalPanel(userService, goalService));
        });

        add(button);
    }

    private void applyTheme(JButton button) {
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Verbose", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void updateContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);
    }
}
