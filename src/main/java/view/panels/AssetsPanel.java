package panels;

import application.AccountService;
import application.AssetService;
import application.PortfolioService;
import application.UserService;
import models.Asset;
import themes.Colors;
import themes.Fonts;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssetsPanel extends JPanel {
    private AccountService accountService = new AccountService();
    private UserService userService = new UserService();
    private AssetService assetService = new AssetService();
    private PortfolioService portfolioService = new PortfolioService();

    private JPanel amountPanel;
    private JPanel portFolioPanel;

    public AssetsPanel() {
        setLayout(new GridBagLayout());
        setOpaque(false);

        initAmount();
        initPortFolio();
//        initAssets();
    }

    private void initAmount() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 20, 0, 20);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;

        amountPanel = new JPanel(new BorderLayout());
        amountPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 210, 210)));


        amountPanel.add(createAmountPanel(), BorderLayout.PAGE_START);

        amountPanel.setOpaque(false);

        add(amountPanel, gridBagConstraints);
    }

    private void initPortFolio() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 20, 0, 20);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;

        portFolioPanel = new JPanel(new BorderLayout());
        portFolioPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 210, 210)));

        JLabel titleLabel = new JLabel("보유자산 상위 5개 포트폴리오");
        titleLabel.setBorder(new EmptyBorder(5, 0, 5, 0));
        titleLabel.setFont(Fonts.MEDIUM_BOLD);

        portFolioPanel.add(titleLabel, BorderLayout.PAGE_START);
        portFolioPanel.add(createPortFolioPanel());

        portFolioPanel.setOpaque(false);

        add(portFolioPanel, gridBagConstraints);
    }

    private JPanel createAmountPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        panel.setPreferredSize(new Dimension(300, 120));

        JLabel titleLabel = new JLabel("내 보유자산");
        titleLabel.setFont(Fonts.MEDIUM_BOLD);

        JLabel cashLabel = new JLabel("현금");
        cashLabel.setFont(Fonts.SMALL_BOLD);

        JLabel totalAmountLabel = new JLabel("총 보유자산");
        totalAmountLabel.setFont(Fonts.SMALL_BOLD);

        panel.add(titleLabel);
        panel.add(new JLabel(""));
        panel.add(cashLabel);
        panel.add(new JLabel(accountService.cash() + " 원"));
        panel.add(totalAmountLabel);
        panel.add(new JLabel(userService.totalAmount() + " 원"));
        panel.add(new JLabel("총 매수 " + assetService.totalPurchase() + " 원"));
        panel.add(new JLabel("평가손익 " + assetService.income() + " 원"));
        panel.add(new JLabel("총 평가 " + assetService.valuation() + " 원"));
        panel.add(new JLabel("수익률 " + assetService.performance() + "%"));

        panel.setOpaque(false);

        return panel;
    }

    private JPanel createPortFolioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.setPreferredSize(new Dimension(300, 155));
        panel.setBorder(new EmptyBorder(0, 0, 5, 0));

        panel.add(new PieChart());

        JPanel assetListPanel = new JPanel();
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(10);
        assetListPanel.setLayout(layout);
        assetListPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        HashMap<String, Double> assets = portfolioService.assets(5);

        int index = 0;

        for (String key : assets.keySet()) {
            JLabel label = new JLabel(key);
            label.setFont(Fonts.MEDIUM);
            label.setBorder(new CompoundBorder(
                    BorderFactory.createMatteBorder(0, 20, 0, 0, Colors.CHART_COLORS.get(index)),
                    BorderFactory.createEmptyBorder(0, 5, 0, 0)));

            assetListPanel.add(label);

            index += 1;
        }

        assetListPanel.setOpaque(false);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());

        wrapperPanel.add(assetListPanel, BorderLayout.PAGE_START);

        wrapperPanel.setOpaque(false);

        panel.add(wrapperPanel);

        panel.setOpaque(false);

        return panel;
    }

    class Slice {
        double value;
        Color color;

        public Slice(double value, Color color) {
            this.value = value;
            this.color = color;
        }
    }

    class PieChart extends JComponent {
        HashMap<String, Double> assets = portfolioService.assets(5);

        List<Slice> slices = new ArrayList<>();

        public void paint(Graphics g) {
            int index = 0;

            for (String key : assets.keySet()) {
                slices.add(new Slice(assets.get(key), Colors.CHART_COLORS.get(index)));

                index += 1;
            }

            drawPie((Graphics2D) g, getBounds(), slices);
        }

        void drawPie(Graphics2D g, Rectangle area, List<Slice> slices) {
            double total = 0.0D;

            for (int i = 0; i < slices.size(); i += 1) {
                total += slices.get(i).value;
            }

            double currentValue = 0.0D;
            int startAngle;

            for (int i = 0; i < slices.size(); i++) {
                startAngle = (int) (currentValue * 360 / total);
                int arcAngle = (int) (slices.get(i).value * 360 / total);

                g.setColor(slices.get(i).color);
                g.fillArc(area.x, area.y, area.width, area.height,
                        startAngle, arcAngle);

                currentValue += slices.get(i).value;
            }
        }
    }
}
