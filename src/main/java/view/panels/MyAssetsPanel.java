package panels;

import application.AccountService;
import application.AssetService;
import application.PortfolioService;
import application.TransactionService;
import application.UserService;
import frames.RegisterAssetsFrame;
import themes.Colors;
import themes.Fonts;
import view.frames.CurrentPriceModifyingFrame;
import view.frames.TransactionsFrame;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyAssetsPanel extends JPanel {
    private JPanel topPanel;
    private JPanel contentPanel;
    private JButton assetsTabButton;
    private JButton transactionTabButton;
    private JFrame registerAssetsWindow;
    private JFrame currentPriceModifyingWindow;
    private JFrame transactionsWindow;

    private AccountService accountService;
    private UserService userService;
    private AssetService assetService;
    private PortfolioService portfolioService;
    private TransactionService transactionService;

    private String currentTab = "보유 자산";

    public MyAssetsPanel(AccountService accountService, UserService userService,
                         AssetService assetService, PortfolioService portfolioService, TransactionService transactionService) {
        this.accountService = accountService;
        this.userService = userService;
        this.assetService = assetService;
        this.portfolioService = portfolioService;
        this.transactionService = transactionService;

        setLayout(new BorderLayout());
        setOpaque(false);

        initTopPanel();
        initButtons();
        initTitle();
        initTabs();
        initContentPanel();
    }

    private void initTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0, 1));
        topPanel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(150, 150, 150)),
                BorderFactory.createEmptyBorder(5, 15, 0, 10)));
        topPanel.setOpaque(false);

        add(topPanel, BorderLayout.PAGE_START);
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setOpaque(false);

        buttonPanel.add(createRegisterButton());
        buttonPanel.add(createTransactionButton());
        buttonPanel.add(createCurrentPriceButton());

        topPanel.add(buttonPanel);
    }

    private JButton createRegisterButton() {
        JButton registerButton = new JButton("자산");
        applyTheme(registerButton);

        registerButton.addActionListener(event -> {
            if (registerAssetsWindow != null && registerAssetsWindow.isDisplayable()) {
                return;
            }

            registerAssetsWindow = new RegisterAssetsFrame(accountService, assetService);

            registerAssetsWindow.setVisible(true);

            registerAssetsWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (currentTab.equals("보유 자산")) {
                        updateContentPanel(new panels.AssetsPanel(accountService, userService, assetService, portfolioService));
                    }

                    if (currentTab.equals("입출금 내역")) {
                        updateContentPanel(new panels.TransactionsPanel(transactionService));
                    }
                }
            });
        });

        return registerButton;
    }

    private JButton createTransactionButton() {
        JButton registerButton = new JButton("입출금");
        applyTheme(registerButton);

        registerButton.addActionListener(event -> {
            if (transactionsWindow != null && transactionsWindow.isDisplayable()) {
                return;
            }

            transactionsWindow = new TransactionsFrame(accountService, transactionService);

            transactionsWindow.setVisible(true);

            transactionsWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (currentTab.equals("보유 자산")) {
                        updateContentPanel(new panels.AssetsPanel(accountService, userService, assetService, portfolioService));
                    }

                    if (currentTab.equals("입출금 내역")) {
                        updateContentPanel(new panels.TransactionsPanel(transactionService));
                    }
                }
            });
        });

        return registerButton;
    }

    private JButton createCurrentPriceButton() {
        JButton registerButton = new JButton("현재가");
        applyTheme(registerButton);

        registerButton.addActionListener(event -> {
            if (currentPriceModifyingWindow != null && currentPriceModifyingWindow.isDisplayable()) {
                return;
            }

            currentPriceModifyingWindow = new CurrentPriceModifyingFrame(assetService);

            currentPriceModifyingWindow.setVisible(true);

            currentPriceModifyingWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (currentTab.equals("보유 자산")) {
                        updateContentPanel(new panels.AssetsPanel(accountService, userService, assetService, portfolioService));
                    }

                    if (currentTab.equals("입출금 내역")) {
                        updateContentPanel(new panels.TransactionsPanel(transactionService));
                    }
                }
            });
        });

        return registerButton;
    }

    private void initTitle() {
        JLabel label = new JLabel("나의 자산");
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.LARGE_BOLD);
        topPanel.add(label);
    }

    private void initContentPanel() {
        contentPanel = new JPanel();

        contentPanel.setLayout(new GridLayout());
        contentPanel.add(new panels.AssetsPanel(accountService, userService, assetService, portfolioService));

        contentPanel.setOpaque(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(contentPanel, BorderLayout.PAGE_START);

        panel.setOpaque(false);

        add(panel, BorderLayout.CENTER);
    }

    private void initTabs() {
        JPanel tabPanel = new JPanel();
        tabPanel.setLayout(new GridLayout(1, 2));
        tabPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

        tabPanel.add(createAssetsTabButton());
        tabPanel.add(createTransactionTabButton());

        tabPanel.setOpaque(false);

        topPanel.add(tabPanel);
    }

    private JButton createAssetsTabButton() {
        assetsTabButton = new JButton("보유 자산");

        assetsTabButton.setBorderPainted(false);
        assetsTabButton.setFont(Fonts.MEDIUM_BOLD);
        assetsTabButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        assetsTabButton.addActionListener(event -> {
            currentTab = "보유 자산";
            assetsTabButton.setFont(Fonts.MEDIUM_BOLD);
            transactionTabButton.setFont(Fonts.MEDIUM);

            updateContentPanel(new panels.AssetsPanel(accountService, userService, assetService, portfolioService));
        });

        return assetsTabButton;
    }

    private JButton createTransactionTabButton() {
        transactionTabButton = new JButton("입출금 내역");

        transactionTabButton.setBorderPainted(false);
        transactionTabButton.setFont(Fonts.MEDIUM);
        transactionTabButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        transactionTabButton.addActionListener(event -> {
            currentTab = "입출금 내역";
            assetsTabButton.setFont(Fonts.MEDIUM);
            transactionTabButton.setFont(Fonts.MEDIUM_BOLD);

            updateContentPanel(new panels.TransactionsPanel(transactionService));
        });

        return transactionTabButton;
    }

    private void updateContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.PAGE_START);
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);
    }

    private void applyTheme(JButton button) {
        button.setFont(new Font("Verbose", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
