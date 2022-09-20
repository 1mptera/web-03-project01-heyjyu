package frames;

import models.InvestGod;
import models.User;
import panels.JournalsPanel;
import panels.NavigatorPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;

public class MainFrame extends JFrame {
    private User user;

    private JPanel contentPanel;

    public MainFrame(InvestGod investGod) throws IOException {
        this.user = investGod.user();

        setTitle("Invest God");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setSize(350, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        initContentPanel();
        initNavigatorPanel();
    }

    private void initContentPanel() throws IOException {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout());
        contentPanel.setBackground(Color.WHITE);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        initJournalsPanel();

        add(contentPanel, gridBagConstraints);
    }

    private void initJournalsPanel() throws IOException {
        contentPanel.add(new JournalsPanel(user));
    }

    private void initNavigatorPanel() throws IOException {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(new NavigatorPanel(contentPanel, user), gridBagConstraints);
    }
}
