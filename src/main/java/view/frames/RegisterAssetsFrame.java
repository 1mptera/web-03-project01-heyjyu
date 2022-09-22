package frames;

import application.AccountService;
import application.AssetService;
import themes.Colors;
import themes.Fonts;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class RegisterAssetsFrame extends JFrame {
    private AccountService accountService;
    private AssetService assetService;

    private JPanel contentPanel;
    private JTextField cashField = new JTextField(8);
    private JTextField nameField = new JTextField(8);
    private JTextField averagePriceField = new JTextField(8);
    private JTextField countField = new JTextField(8);
    private JTextField currentUnitPriceField = new JTextField(8);

    public RegisterAssetsFrame(AccountService accountService, AssetService assetService) {
        this.accountService = accountService;
        this.assetService = assetService;

        setTitle("자산 등록");
        setSize(new Dimension(300, 350));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initTabs();
        initContentPanel();
    }

    private void initTabs() {
        JPanel panel = new JPanel();
        panel.setBackground(Colors.BACKGROUND);

        panel.add(createCashTabButton());
        panel.add(createAssetsTabButton());

        add(panel, BorderLayout.PAGE_START);
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        contentPanel.setBackground(Colors.BACKGROUND);

        contentPanel.add(createRegisterCashPanel());

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.add(contentPanel, BorderLayout.PAGE_START);
        wrapperPanel.setBackground(Colors.BACKGROUND);

        add(wrapperPanel);
    }

    private JButton createCashTabButton() {
        JButton button = new JButton("현금");

        button.addActionListener(event -> {
            updateContentPanel(createRegisterCashPanel());
        });

        return button;
    }

    private JButton createAssetsTabButton() {
        JButton button = new JButton("자산");

        button.addActionListener(event -> {
            updateContentPanel(createRegisterAssetsPanel());
        });

        return button;
    }

    private JPanel createRegisterCashPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JLabel label = new JLabel("현금 보유액");
        label.setFont(Fonts.SMALL);
        panel.add(label);
        panel.add(cashField);

        JButton button = new JButton("등록");

        button.addActionListener(event -> {
            accountService.modifyCash(Double.valueOf(cashField.getText()));

            dispose();
        });

        panel.add(button);

        panel.setOpaque(false);

        return panel;
    }

    private JPanel createRegisterAssetsPanel() {
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(2);
        panel.setLayout(layout);

        panel.add(new JLabel("자산 이름"));
        panel.add(nameField);
        panel.add(new JLabel("매수 평균가"));
        panel.add(averagePriceField);
        panel.add(new JLabel("보유 수량"));
        panel.add(countField);
        panel.add(new JLabel("현재 단가"));
        panel.add(currentUnitPriceField);

        JButton button = new JButton("등록");

        button.addActionListener(event -> {
            assetService.add(nameField.getText(),
                    Double.valueOf(averagePriceField.getText()),
                    Double.valueOf(countField.getText()),
                    Double.valueOf(currentUnitPriceField.getText()));

            resetFields();
        });

        panel.add(button);

        panel.setOpaque(false);

        return panel;
    }

    private void resetFields() {
        nameField.setText("");
        averagePriceField.setText("");
        countField.setText("");
        currentUnitPriceField.setText("");
    }

    private void updateContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);
    }
}
