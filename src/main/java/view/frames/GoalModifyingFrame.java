package view.frames;

import application.GoalService;
import themes.Colors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class GoalModifyingFrame extends JFrame {
    private GoalService goalService;
    private JPanel contentPanel;
    private JTextField field;

    public GoalModifyingFrame(GoalService goalService) {
        this.goalService = goalService;

        setTitle("목표 수정");
        setLayout(new GridLayout());
        setSize(new Dimension(300, 150));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initContentPanel();
        initTitle();
        initField();
        initButton();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 0));
        contentPanel.setBackground(Colors.BACKGROUND);

        add(contentPanel);
    }

    private void initTitle() {
        contentPanel.add(new JLabel("목표"));
    }

    private void initField() {
        field = new JTextField(10);

        contentPanel.add(field);
    }

    private void initButton() {
        JButton button = new JButton("수정");

        button.addActionListener(event -> {
            goalService.modifyAmount(Double.parseDouble(field.getText()));

            dispose();
        });

        contentPanel.add(button);
    }
}
