package panels;

import application.TransactionService;
import models.Resources;
import themes.Colors;
import themes.Fonts;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.UUID;

public class TransactionsPanel extends JPanel {
    private JPanel topPanel;
    private JPanel transactionsPanel;

    private TransactionService transactionService;

    public TransactionsPanel(TransactionService transactionService) {
        this.transactionService = transactionService;

        setLayout(new BorderLayout());
        setOpaque(false);

        initTopPanel();
        initTitle();
        initTransactionsPanel();
    }

    private void initTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());
        topPanel.setBorder(new CompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(150, 150, 150)),
                BorderFactory.createEmptyBorder(15, 15, 15, 10)));
        topPanel.setOpaque(false);

        add(topPanel, BorderLayout.PAGE_START);
    }

    private void initTitle() {
        JLabel label = new JLabel("입출금 내역");
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.LARGE_BOLD);
        topPanel.add(label);
    }

    private JScrollPane createTransactionsPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));

        for (UUID id : transactionService.getIds()) {
            panel.add(createTransactionPanel(id));
        }

        panel.setOpaque(false);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());

        wrapperPanel.add(panel, BorderLayout.PAGE_START);

        wrapperPanel.setOpaque(false);

        JScrollPane jScrollPane = new JScrollPane(wrapperPanel);
        jScrollPane.setPreferredSize(new Dimension(300, 530));
        jScrollPane.setBorder(null);

        jScrollPane.setOpaque(false);

        return jScrollPane;
    }

    private JPanel createTransactionPanel(UUID transactionId) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));

        JLabel typeLabel = new JLabel(transactionService.type(transactionId));
        typeLabel.setFont(Fonts.MEDIUM_BOLD);
        typeLabel.setHorizontalAlignment(JLabel.LEFT);

        panel.add(typeLabel);

        JLabel amountLabel = new JLabel(transactionService.amount(transactionId) + "원");
        amountLabel.setHorizontalAlignment(JLabel.RIGHT);

        panel.add(amountLabel);

        panel.setOpaque(false);

        return panel;
    }

    private void initTransactionsPanel() {
        transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new BorderLayout());

        transactionsPanel.add(createTransactionsPanel(), BorderLayout.PAGE_START);

        transactionsPanel.setOpaque(false);

        add(transactionsPanel, BorderLayout.CENTER);
    }
}
