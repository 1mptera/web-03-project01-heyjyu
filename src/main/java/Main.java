import frames.MainFrame;

import javax.swing.JFrame;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Main application = new Main();

        application.run();
    }

    private void run() throws IOException {
        JFrame mainFrame = new MainFrame();

        mainFrame.setVisible(true);
    }
}
