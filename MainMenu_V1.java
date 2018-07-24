import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu_V1 extends JFrame {

    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem exit;
    private JPanel mainPanel;

    public MainMenu_V1() {
        setTitle("Main Panel");
        setResizable(false);
        setSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMainMenu());
        setLocationRelativeTo(null);
        setResizable(true);

        setCards();

    }

    public JMenuBar createMainMenu() {
        menuBar = new JMenuBar();
        file = new JMenu("File");
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        file.add(exit);
        menuBar.add(file);

        return menuBar;
    }

    public void switchPanel(Container container, String panelName) {
        CardLayout card = (CardLayout) (container.getLayout());
        card.show(container, panelName);
    }

    public void setCards() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        mainPanel.add(new LoginPanel(mainPanel), "LOGIN");
        mainPanel.add(new MainOrderWindow(mainPanel), "MENU");

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu_V1().setVisible(true);
            }
        });
    }
}