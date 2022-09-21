package panels;

import frames.RegisterAssetsFrame;
import themes.Colors;
import themes.Fonts;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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

    public MyAssetsPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        initTopPanel();
        initButtons();
        initTitle();
        initTabs();
        initScrollPanel();
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

            registerAssetsWindow = new RegisterAssetsFrame();

            registerAssetsWindow.setVisible(true);

            registerAssetsWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateContentPanel(new panels.AssetsPanel());
                }
            });
        });

        return registerButton;
    }

    private JButton createTransactionButton() {
        JButton registerButton = new JButton("입출금");
        applyTheme(registerButton);

        registerButton.addActionListener(event -> {
            //TODO
        });

        return registerButton;
    }

    private JButton createCurrentPriceButton() {
        JButton registerButton = new JButton("현재가");
        applyTheme(registerButton);

        registerButton.addActionListener(event -> {
            //TODO
        });

        return registerButton;
    }

    private void initTitle() {
        JLabel label = new JLabel("나의 자산");
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.LARGE_BOLD);
        topPanel.add(label);
    }

    private JScrollPane createScrollPanel() {
        contentPanel = new JPanel();

        contentPanel.setLayout(new GridLayout());
        contentPanel.add(new panels.AssetsPanel());

        contentPanel.setOpaque(false);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());

        wrapperPanel.add(contentPanel, BorderLayout.PAGE_START);

        wrapperPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setPreferredSize(new Dimension(300, 500));
        scrollPane.setBorder(null);

        scrollPane.setOpaque(false);

        return scrollPane;
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
            assetsTabButton.setFont(Fonts.MEDIUM_BOLD);
            transactionTabButton.setFont(Fonts.MEDIUM);

            updateContentPanel(new panels.AssetsPanel());
        });

        return assetsTabButton;
    }

    private JButton createTransactionTabButton() {
        transactionTabButton = new JButton("입출금 내역");

        transactionTabButton.setBorderPainted(false);
        transactionTabButton.setFont(Fonts.MEDIUM);
        transactionTabButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        transactionTabButton.addActionListener(event -> {
            assetsTabButton.setFont(Fonts.MEDIUM);
            transactionTabButton.setFont(Fonts.MEDIUM_BOLD);

            updateContentPanel(new panels.TransactionsPanel());
        });

        return transactionTabButton;
    }

    private void initScrollPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(createScrollPanel(), BorderLayout.PAGE_START);

        panel.setOpaque(false);

        add(panel, BorderLayout.CENTER);
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
