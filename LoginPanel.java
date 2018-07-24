import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    //is logged in as public so dont need to resign in after logged in
    public boolean isLoggedIn = false;

    private JPanel mainPanel;
    private final JTextField tfUsername = new JTextField(20);
    private final JPasswordField pfPassword = new JPasswordField(20);

    public LoginPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        setPreferredSize(new Dimension(400, 600));
        setBackground(Color.GRAY);
        add(loginFrame());
        add(createButton());

    }

    private JButton createButton() {
        JButton button = new JButton("Login");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu_V1 main = new MainMenu_V1();
                isLoggedIn();
                //System.out.println(isLoggedIn);
                if (isLoggedIn)
                {
                    main.switchPanel(mainPanel, "MENU");
                }
            }
        });
        return button;
    }

    private JPanel loginFrame() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        JLabel lbUsername = new JLabel("Username: ", SwingConstants.CENTER);
        panel.add(lbUsername);

        panel.add(tfUsername);
        JLabel lbPassword = new JLabel("Password: ", SwingConstants.CENTER);
        panel.add(lbPassword);

        panel.add(pfPassword);
        panel.add(new JLabel("", SwingConstants.CENTER));

        return panel;
    }

    private void isLoggedIn()
    {
        isLoggedIn = false;

        //JPassword is type char[] need to convert to string to test agains string pw defined
        String passText = new String(pfPassword.getPassword());

        //check if the passwrod is correct
        //TODO create employee database and set usetname and pw to check against login
        String userName = "root";
        String password = "root";
        if (tfUsername.getText().equals(userName) && passText.equals(password)) {
            JOptionPane.showMessageDialog(null, "Successfully logged in!"
                    , "Success", JOptionPane.INFORMATION_MESSAGE);
            isLoggedIn = true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Please enter correct username and password"
                    , "Try Again", JOptionPane.INFORMATION_MESSAGE);
            isLoggedIn = false;
        }
    }


}