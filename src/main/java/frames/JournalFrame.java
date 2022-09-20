package frames;

import models.InvestGod;
import models.Journal;
import themes.Colors;
import themes.Fonts;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

public class JournalFrame extends JFrame {
    private InvestGod investGod;
    private Journal journal;
    private JPanel contentPanel;

    public JournalFrame(InvestGod investGod, Journal journal) {
        this.investGod = investGod;
        this.journal = journal;
        setTitle("일지");
        setLayout(new GridLayout());
        setSize(new Dimension(300, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initContentPanel();
        initButton();
        initDate();
        initTitle();
        initContent();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 0));
        contentPanel.setBackground(Colors.BACKGROUND);

        add(contentPanel);
    }

    private void initButton() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton button = new JButton("수정");

        button.addActionListener(event -> {

        });

        panel.add(button);

        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void initDate() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;

        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(journal.title());

        titleLabel.setForeground(Colors.FONT);
        titleLabel.setFont(Fonts.LARGE);

        panel.add(titleLabel, BorderLayout.PAGE_START);

        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }

    private void initContent() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(10, 0, 0, 20);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        JPanel panel = new JPanel(new BorderLayout());
        JPanel journalPanel = new JPanel(new GridLayout(0, 1));

        String[] lines = journal.content().split("\\n");

        for (String line : lines) {
            JLabel contentLabel = new JLabel(line);

            contentLabel.setForeground(Colors.FONT);
            contentLabel.setFont(Fonts.SMALL);
            journalPanel.add(contentLabel);
        }

        journalPanel.setOpaque(false);

        panel.add(journalPanel, BorderLayout.PAGE_START);

        panel.setOpaque(false);

        contentPanel.add(panel, gridBagConstraints);
    }
}
