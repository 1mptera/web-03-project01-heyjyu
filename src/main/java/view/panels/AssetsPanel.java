package panels;

import application.AccountService;
import application.AssetService;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

public class AssetsPanel extends JPanel {
    public AssetsPanel() {
        add(new JLabel("자산 개수" + new AssetService().assets().size()));
        add(new JLabel(String.valueOf(new AccountService().cash())));
    }
}
