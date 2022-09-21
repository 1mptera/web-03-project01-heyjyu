package frames;

import application.JournalService;
import models.User;
import models.Journal;
import models.Trading;
import themes.Colors;
import themes.Fonts;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JournalingFrame extends JFrame {
    private List<Trading> tradings = new ArrayList<>();

    private JPanel contentPanel;
    private JTextField textField;
    private JTextArea textArea;
    private JPanel tradingsPanel;
    private JTextField nameField;
    private JRadioButton buyButton;
    private JRadioButton sellButton;
    private JTextField unitPriceField;
    private JTextField countField;
    private JPanel tradingPanel;
    private JournalService journalService = new JournalService();

    public JournalingFrame() {
        setTitle("일지 작성");
        setLayout(new GridLayout());
        setSize(new Dimension(300, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initContentPanel();
        initDate();
        initTitleField();
        initContentField();
        initTradingsPanel();
        initTradingsField();
        initButtons();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        contentPanel.setBackground(Colors.BACKGROUND);

        add(contentPanel);
    }

    private void initDate() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;

        LocalDate now = LocalDate.now();

        JLabel label = new JLabel(now.getYear() + " " + now.getMonth() + " " + now.getDayOfMonth());
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.LARGE);
        contentPanel.add(label, gridBagConstraints);
    }

    private void initTitleField() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(15, 0, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        JLabel label = new JLabel("제목");
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.MEDIUM);
        panel.add(label);

        textField = new JTextField(10);
        textField.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)),
                BorderFactory.createLineBorder(Color.WHITE, 5)));
        panel.add(textField);
        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void initContentField() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("내용");
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.MEDIUM);
        panel.add(label, BorderLayout.PAGE_START);

        textArea = new JTextArea();
        textArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 5));

        panel.add(new JScrollPane(textArea));
        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void initTradingsPanel() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;

        tradingPanel = new JPanel();
        tradingPanel.setPreferredSize(new Dimension(240, 50));
        tradingPanel.setLayout(new BorderLayout());

        createTradingsPanel();

        tradingPanel.add(createTradingsPanel());
        tradingPanel.setOpaque(false);

        contentPanel.add(tradingPanel, gridBagConstraints);
    }

    private JScrollPane createTradingsPanel() {
        JLabel label = new JLabel("거래 내역");
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.MEDIUM);
        tradingPanel.add(label, BorderLayout.PAGE_START);

        tradingsPanel = new JPanel();
        tradingsPanel.setLayout(new GridLayout(0, 1));
        tradingsPanel.setOpaque(false);

        for (Trading trading : tradings) {
            tradingsPanel.add(new JLabel(trading.toString()));
        }

        return new JScrollPane(tradingsPanel);
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

        nameField = new JTextField(10);

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("자산명"));
        namePanel.add(nameField);
        namePanel.setOpaque(false);

        panel.add(namePanel);

        JPanel buttonsPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        buyButton = new JRadioButton("매수");
        buyButton.setSelected(true);
        sellButton = new JRadioButton("매도");
        group.add(buyButton);
        group.add(sellButton);
        buttonsPanel.add(buyButton);
        buttonsPanel.add(sellButton);
        buttonsPanel.setOpaque(false);

        panel.add(buttonsPanel);

        unitPriceField = new JTextField(6);
        countField = new JTextField(6);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.add(new JLabel("단가 (원)"));
        fieldsPanel.add(unitPriceField);
        fieldsPanel.add(new JLabel("개수"));
        fieldsPanel.add(countField);
        fieldsPanel.setOpaque(false);

        panel.add(fieldsPanel);
        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void initButtons() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1.0;

        JPanel panel = new JPanel();

        JButton addButton = new JButton("거래 내역 추가");
        addButton.setForeground(Colors.FONT);
        addButton.setFont(Fonts.MEDIUM);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addButton.addActionListener(event -> {
            String name = nameField.getText();
            String type = "매수";

            if (sellButton.isSelected()) {
                type = "매도";
            }

            Double unitPrice = Double.parseDouble(unitPriceField.getText());
            Double count = Double.parseDouble(countField.getText());

            tradings.add(new Trading(name, type, unitPrice, count));

            resetFields();
            updateTradingPanel();
        });

        JButton saveButton = new JButton("작성");
        saveButton.setForeground(Colors.FONT);
        saveButton.setFont(Fonts.MEDIUM);
        saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        saveButton.addActionListener(event -> {
            String title = textField.getText();
            String content = textArea.getText();

            if (title.equals("") || content.equals("")) {
                return;
            }

            journalService.writeJournal(title, content);

            dispose();
        });

        panel.add(addButton);
        panel.add(saveButton);
        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void updateTradingPanel() {
        tradingPanel.removeAll();
        tradingPanel.add(createTradingsPanel());
        tradingPanel.setVisible(false);
        tradingPanel.setVisible(true);
    }

    private void resetFields() {
        nameField.setText("");
        sellButton.setSelected(true);
        unitPriceField.setText("");
        countField.setText("");
    }
}
