package view.frames;

import application.AccountService;
import application.TransactionService;
import themes.Colors;
import themes.Fonts;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;

public class TransactionsFrame extends JFrame {
    private JPanel contentPanel;
    private JTextField amountField;
    private JRadioButton transferButton;
    private JRadioButton receiveButton;
    private JTextField memoField;

    private TransactionService transactionService;
    private AccountService accountService;

    public TransactionsFrame(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;

        setTitle("입출금 내역 등록");
        setLayout(new GridLayout());
        setSize(new Dimension(300, 200));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initContentPanel();
        initTradingsField();
        initButton();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        contentPanel.setBackground(Colors.BACKGROUND);

        add(contentPanel);
    }

    private void initTradingsField() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JPanel buttonsPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        receiveButton = new JRadioButton("입금");
        receiveButton.setSelected(true);
        transferButton = new JRadioButton("출금");
        group.add(receiveButton);
        group.add(transferButton);
        buttonsPanel.add(receiveButton);
        buttonsPanel.add(transferButton);
        buttonsPanel.setOpaque(false);

        panel.add(buttonsPanel);

        amountField = new JTextField(10);

        JPanel amountPanel = new JPanel();
        amountPanel.add(new JLabel("금액"));
        amountPanel.add(amountField);
        amountPanel.setOpaque(false);

        panel.add(amountPanel);

        memoField = new JTextField(10);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.add(new JLabel("메모"));
        fieldsPanel.add(memoField);
        fieldsPanel.setOpaque(false);

        panel.add(fieldsPanel);
        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void initButton() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;

        JPanel panel = new JPanel();

        JButton addButton = new JButton("등록");
        addButton.setForeground(Colors.FONT);
        addButton.setFont(Fonts.MEDIUM);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButton.addActionListener(event -> {
            String amount = amountField.getText();
            String memo = memoField.getText();
            String type = "입금";

            if (transferButton.isSelected()) {
                type = "출금";
            }

            if (amount.equals("")) {
                return;
            }

            try {
                transactionService.add(type, Double.valueOf(amount), memo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                accountService.process(type, Double.valueOf(amount));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            resetFields();
        });

        panel.add(addButton);
        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void resetFields() {
        amountField.setText("");
        memoField.setText("");
    }
}
