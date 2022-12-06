package snakeparty;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;

import ocsf.client.AbstractClient;

import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LoginPanel implements ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private AbstractClient client;
    private GamePanel gamePanel;
    private JTextField jtUsername;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton returnToLoginBtn;
    private JPanel panel_1;
    private JPanel panel_2;

    public LoginPanel(JFrame frame, AbstractClient client) {
        this.frame = frame;
        this.client = client;
    }

    public void initializePanel() {
        frame.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 450, 0 };
        gbl_panel.rowHeights = new int[] { 30, 84, 45, 96, 23, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setFont(new Font("Kokonor", Font.PLAIN, 28));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        panel.add(lblNewLabel, gbc_lblNewLabel);

        panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 2;
        panel.add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0 };
        gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
        gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        panel_1.setLayout(gbl_panel_1);

        JLabel lblNewLabel_1 = new JLabel("Username");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 0;
        panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

        jtUsername = new JTextField();
        GridBagConstraints gbc_jtUsername = new GridBagConstraints();
        gbc_jtUsername.insets = new Insets(0, 0, 5, 5);
        gbc_jtUsername.gridx = 2;
        gbc_jtUsername.gridy = 0;
        panel_1.add(jtUsername, gbc_jtUsername);
        jtUsername.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Password");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 1;
        panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);

        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.insets = new Insets(0, 0, 0, 5);
        gbc_passwordField.gridx = 2;
        gbc_passwordField.gridy = 1;
        panel_1.add(passwordField, gbc_passwordField);

        panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 5, 0);
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 3;
        panel.add(panel_2, gbc_panel_2);

        loginBtn = new JButton("login");
        panel_2.add(loginBtn);

        returnToLoginBtn = new JButton("Back");
        panel_2.add(returnToLoginBtn);
        returnToLoginBtn.addActionListener(this);
        loginBtn.addActionListener(this);

    }

    public void success() {
        PanelControl.gamePanel.display();
    }

    public void failure() {
        this.display();
    }

    public void display() {
        frame.getContentPane().removeAll();
        this.initializePanel();
        frame.validate();
        frame.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == loginBtn) {
            try {
                client.openConnection();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            @SuppressWarnings("deprecation")
            LoginData loginData = new LoginData(jtUsername.getText(), passwordField.getText());
            try {
                client.sendToServer(loginData);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (source == returnToLoginBtn) {
            PanelControl.initialPanel.display();
        }
    }
}
