package cs414.a5.nlighth1.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Nathan Lighthart
 */
public class LoginUI {
    private UIController controller;
    private JFrame frame;
    private JPanel panel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField username;
    private JPasswordField password;
    private JLabel statusLabel;
    private JButton loginButton;

    public LoginUI(UIController controller) {
        this.controller = controller;
    }

    public void init() {
        frame = new JFrame("Login");
        panel = new JPanel();
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        username = new JTextField(20);
        password = new JPasswordField(20);
        statusLabel = new JLabel("");
        loginButton = new JButton("Login");

        layoutComponents();

        addListeners();

        frame.pack();
        frame.setResizable(false);
    }

    public void setVisible(boolean visible) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }

    public void clear() {
        username.setText("");
        password.setText("");
    }

    public void setStatus(String status) {
        setStatus(status, Color.RED);
    }

    public void setStatus(String status, Color textColor) {
        statusLabel.setForeground(textColor);
        statusLabel.setText(status);
    }

    private void layoutComponents() {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints;

        // Add status label
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 3, 5);
        panel.add(statusLabel, gridBagConstraints);

        // Add username label
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 5, 3, 5);
        panel.add(usernameLabel, gridBagConstraints);

        // Add username field
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 5, 3, 5);
        panel.add(username, gridBagConstraints);

        // Add password label
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(2, 5, 3, 5);
        panel.add(passwordLabel, gridBagConstraints);

        // Add password field
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(2, 5, 3, 5);
        panel.add(password, gridBagConstraints);

        // Add login button
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(2, 5, 5, 5);
        panel.add(loginButton, gridBagConstraints);

        frame.add(panel);
    }

    private void addListeners() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.closeProgram();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.login(username.getText(), new String(password.getPassword()));
            }
        });
        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.login(username.getText(), new String(password.getPassword()));
            }
        });
    }

    // Used to view the interface with nothing working
    public static void main(String[] args) {
        final LoginUI view = new LoginUI(null);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.init();
                view.setVisible(true);
            }
        });
    }

}
