package view.frames;

import application.AssetService;
import models.Asset;
import themes.Colors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentPriceModifyingFrame extends JFrame {
    private AssetService assetService;

    private JPanel contentPanel;
    private List<JTextField> fields = new ArrayList<>();

    public CurrentPriceModifyingFrame() {
        assetService = new AssetService();
        for (int i = 0; i < assetService.assets().size(); i += 1) {
            JTextField textField = new JTextField();
            textField.setText(String.valueOf(assetService.assets().get(i).currentUnitPrice()));

            fields.add(textField);
        }

        setTitle("현재단가 수정");
        setLayout(new GridLayout());
        setSize(new Dimension(300, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initContentPanel();
        initButton();
        initList();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        contentPanel.setOpaque(false);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.setBackground(Colors.BACKGROUND);
        wrapperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        wrapperPanel.add(contentPanel, BorderLayout.PAGE_START);

        add(wrapperPanel);
    }

    private void initButton() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton button = new JButton("확인");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(event -> {
            List<Double> prices = new ArrayList<>();

            for (JTextField field : fields) {
                prices.add(Double.valueOf(field.getText()));
            }

            if (assetService.assets().size() > 0) {
                assetService.updateCurrentPrices(prices);
            }

            dispose();
        });

        panel.add(button);

        panel.setOpaque(false);

        contentPanel.add(panel, BorderLayout.PAGE_START);
    }

    private void initList() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        panel.setBackground(Colors.BACKGROUND);

        for (int i = 0; i < assetService.assets().size(); i += 1) {
            panel.add(new JLabel(assetService.assets().get(i).name()));
            panel.add(fields.get(i));
        }

        JScrollPane jScrollPane = new JScrollPane(panel);
        jScrollPane.setPreferredSize(new Dimension(300, 210));
        jScrollPane.setBorder(null);

        jScrollPane.setOpaque(false);

        contentPanel.add(jScrollPane);

        contentPanel.add(panel);
    }
}
