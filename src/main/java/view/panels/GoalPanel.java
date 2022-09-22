package panels;

import application.AccountService;
import application.GoalService;
import application.UserService;
import models.Goal;
import models.Resources;
import themes.Colors;
import themes.Fonts;
import view.frames.GoalModifyingFrame;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;

public class GoalPanel extends JPanel {
    private UserService userService;
    private GoalService goalService;

    private final DecimalFormat decimalFormat;

    private JPanel contentPanel;
    private GoalModifyingFrame goalModifyingWindow;

    public GoalPanel(UserService userService, GoalService goalService) {
        this.userService = userService;
        this.goalService = goalService;

        decimalFormat = new DecimalFormat("#");
        decimalFormat.setMaximumFractionDigits(0);

        setLayout(new GridBagLayout());
        setOpaque(false);

        initContentPanel();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setBorder(new EmptyBorder(30, 10, 10, 10));

        contentPanel.add(createContentPanel());

        contentPanel.setOpaque(false);

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(30);
        panel.setLayout(layout);

        panel.add(contentPanel);
        panel.add(createStar());

        panel.setOpaque(false);

        add(panel);
    }

    private JPanel createContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        panel.add(createTitlePanel());
        panel.add(createGoalPanel());
        panel.add(createStatusPanel());
        panel.add(createProgressBarPanel());

        panel.setOpaque(false);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());

        wrapperPanel.add(panel, BorderLayout.PAGE_START);

        wrapperPanel.setOpaque(false);

        return wrapperPanel;
    }

    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();

        JLabel label = new JLabel("나의 목표");
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.LARGE_BOLD);

        panel.add(label);

        panel.setOpaque(false);

        return panel;
    }

    private JPanel createGoalPanel() {
        JPanel panel = new JPanel();

        JLabel goalLabel = new JLabel(decimalFormat.format(goalService.amount()) + " 원");
        goalLabel.setForeground(Colors.FONT);
        goalLabel.setFont(Fonts.MEDIUM_BOLD);

        panel.add(goalLabel);

        JButton button = new JButton("수정");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(event -> {
            if (goalModifyingWindow != null && goalModifyingWindow.isDisplayable()) {
                return;
            }

            goalModifyingWindow = new GoalModifyingFrame(goalService);

            goalModifyingWindow.setVisible(true);

            goalModifyingWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateContentPanel();
                }
            });
        });

        panel.add(button);

        panel.setOpaque(false);

        return panel;
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel();

        JLabel label = new JLabel("현재 자산 " + decimalFormat.format(userService.totalAmount()) + " 원");
        label.setForeground(Colors.FONT);
        label.setFont(Fonts.MEDIUM_BOLD);

        panel.add(label);

        panel.setOpaque(false);

        return panel;
    }

    private JPanel createProgressBarPanel() {
        JPanel panel = new JPanel();

        JProgressBar progressBar = new JProgressBar(0, (int) goalService.amount());
        progressBar.setValue((int) Math.round(userService.totalAmount()));

        panel.add(progressBar);

        panel.setOpaque(false);

        return panel;
    }

    private JPanel createStar() {
        JPanel panel = new JPanel();

        ImageIcon imageIcon = new ImageIcon(Resources.MARIO_STAR);
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(resizedImage);

        panel.add(new JLabel(imageIcon));

        panel.setOpaque(false);

        return panel;
    }

    private void updateContentPanel() {
        contentPanel.removeAll();
        contentPanel.add(createContentPanel());
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);
    }
}
