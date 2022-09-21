package frames;

import models.Journal;
import themes.Colors;
import themes.Fonts;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDate;

public class RevisingFrame extends JFrame {
    private JPanel contentPanel;
    private Journal journal;
    private JTextArea textArea;

    public RevisingFrame(Journal journal) {
        this.journal = journal;

        setTitle("일지 수정");
        setLayout(new GridLayout());
        setSize(new Dimension(300, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initContentPanel();
        initDate();
        initTitle();
        initField();
        initButton();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();

        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(Colors.BACKGROUND);

        add(contentPanel);
    }

    private void initDate() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;

        LocalDate date = journal.date();

        JLabel label = new JLabel(date.getYear() + " " + date.getMonth() + " " + date.getDayOfMonth(), JLabel.LEFT);

        label.setForeground(Colors.FONT);
        label.setFont(Fonts.MEDIUM);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setSize(new Dimension(290, 20));

        panel.add(label);

        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void initTitle() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;

        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(journal.title());

        titleLabel.setForeground(Colors.FONT);
        titleLabel.setFont(Fonts.LARGE);

        panel.add(titleLabel, BorderLayout.PAGE_START);

        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void initField() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        textArea = new JTextArea();
        textArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE, 5));

        textArea.setText(journal.content());

        contentPanel.add(new JScrollPane(textArea), gridBagConstraints);
    }

    private void initButton() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;

        JPanel panel = new JPanel();

        JButton button = new JButton("수정");

        button.addActionListener(event -> {
            journal.modify(textArea.getText());

            dispose();
        });

        panel.add(button);

        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }
}
