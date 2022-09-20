import frames.MainFrame;
import models.InvestGod;

import javax.swing.JFrame;
import java.io.IOException;

public class Main {
    private InvestGod investGod;

    public static void main(String[] args) throws IOException {
        Main application = new Main();

        application.run();
    }

    private void run() throws IOException {
        loadData();

        JFrame mainFrame = new MainFrame(investGod);

        mainFrame.setVisible(true);
    }

    private void loadData() {
        investGod = new InvestGod();

        investGod.loadJournals();
    }
}
