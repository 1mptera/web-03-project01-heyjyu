package panels;

import models.User;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class NavigatorPanel extends JPanel {
    private JPanel contentPanel;
    private User user;

    public NavigatorPanel(JPanel contentPanel, User user) throws IOException {
        this.contentPanel = contentPanel;
        this.user = user;
        setPreferredSize(new Dimension(400, 70));
        setLayout(new GridLayout(1, 5));
        setBackground(new Color(0, 37, 88));

        initJournalButton();
        initBookmarkButton();
        initMyAssetsButton();
        initWishListButton();
        initGoalButton();
    }

    private void initJournalButton() throws IOException {
        Image image = ImageIO.read(new File("src/main/resources/images/iconmonstr-file-20-24.png"));
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("일지", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            try {
                updateContentPanel(new JournalsPanel(user));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        add(button);
    }

    private void initBookmarkButton() throws IOException {
        Image image = ImageIO.read(new File("src/main/resources/images/iconmonstr-star-5-24.png"));
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("즐겨찾기", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            updateContentPanel(new BookmarkPanel());
        });

        add(button);
    }

    private void initMyAssetsButton() throws IOException {
        Image image = ImageIO.read(new File("src/main/resources/images/iconmonstr-banknote-8-24.png"));
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("나의 자산", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            updateContentPanel(new MyAssetsPanel());
        });

        add(button);
    }

    private void initWishListButton() throws IOException {
        Image image = ImageIO.read(new File("src/main/resources/images/iconmonstr-favorite-4-24.png"));
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("관심", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            updateContentPanel(new WishListPanel());
        });

        add(button);
    }

    private void initGoalButton() throws IOException {
        Image image = ImageIO.read(new File("src/main/resources/images/iconmonstr-medal-3-24.png"));
        Icon icon = new ImageIcon(image);
        JButton button = new JButton("목표", icon);

        applyTheme(button);

        button.addActionListener(event -> {
            updateContentPanel(new GoalPanel());
        });

        add(button);
    }

    private void applyTheme(JButton button) {
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Verbose", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void updateContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.setVisible(false);
        contentPanel.setVisible(true);
    }
}
