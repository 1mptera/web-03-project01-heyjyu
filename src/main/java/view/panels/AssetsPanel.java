package panels;

import application.AccountService;
import application.AssetService;
import application.UserService;
import themes.Fonts;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class AssetsPanel extends JPanel {
    private JPanel amountPanel;
    private AccountService accountService = new AccountService();
    private UserService userService = new UserService();
    private AssetService assetService = new AssetService();

    public AssetsPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 210, 210)));
        setOpaque(false);

        initAmount();
//        initPortFolio();
//        initAssets();
    }

    private void initAmount() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 20, 0, 20);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;

        amountPanel = new JPanel(new BorderLayout());

        amountPanel.add(createAmountPanel(), BorderLayout.PAGE_START);

        amountPanel.setOpaque(false);

        add(amountPanel, gridBagConstraints);
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
}
