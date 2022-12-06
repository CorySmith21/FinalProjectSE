package snakeparty;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ocsf.client.AbstractClient;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CreateAccountPanel implements ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private JTextField jtUsername;
    private JPasswordField passwordField;
    private JPasswordField verifypasswordField;
    private JButton createAccountBtn;
    private JButton returnToLoginBtn;
    private AbstractClient client;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private JLabel lblNewLabel_1;
    private JLabel lblEnterPassword;

    public CreateAccountPanel(JFrame frame, AbstractClient client) {
        this.frame = frame;
        this.client = client;
    }

    void initializePanel() {
        System.out.print("!!! initializing createAccountPanel");
        frame.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 77, 0 };
        gbl_panel.rowHeights = new int[] { 45, 23, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 0;
        panel.add(panel_1, gbc_panel_1);

        lblNewLabel_1 = new JLabel("Create Account");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

        panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 5, 0);
        gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 2;
        panel.add(panel_2, gbc_panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 102, 0, 0 };
        gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_panel_2.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panel_2.setLayout(gbl_panel_2);

        JLabel lblNewLabel = new JLabel("Enter Username");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 0;
        panel_2.add(lblNewLabel, gbc_lblNewLabel);
        lblNewLabel.setBounds(171, 135, 105, 25);

        jtUsername = new JTextField();
        GridBagConstraints gbc_jtUsername = new GridBagConstraints();
        gbc_jtUsername.fill = GridBagConstraints.HORIZONTAL;
        gbc_jtUsername.insets = new Insets(0, 0, 5, 5);
        gbc_jtUsername.gridx = 3;
        gbc_jtUsername.gridy = 0;
        panel_2.add(jtUsername, gbc_jtUsername);
        jtUsername.setBounds(287, 134, 185, 26);
        jtUsername.setColumns(10);

        lblEnterPassword = new JLabel("Enter Password");
        GridBagConstraints gbc_lblEnterPassword = new GridBagConstraints();
        gbc_lblEnterPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblEnterPassword.gridx = 1;
        gbc_lblEnterPassword.gridy = 1;
        panel_2.add(lblEnterPassword, gbc_lblEnterPassword);
        lblEnterPassword.setBounds(171, 184, 105, 25);

        verifypasswordField = new JPasswordField();
        GridBagConstraints gbc_verifypasswordField = new GridBagConstraints();
        gbc_verifypasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_verifypasswordField.insets = new Insets(0, 0, 5, 5);
        gbc_verifypasswordField.gridx = 3;
        gbc_verifypasswordField.gridy = 1;
        panel_2.add(verifypasswordField, gbc_verifypasswordField);
        verifypasswordField.setBounds(287, 233, 185, 25);

        JLabel lblVerifyPassword = new JLabel("Verify Password");
        GridBagConstraints gbc_lblVerifyPassword = new GridBagConstraints();
        gbc_lblVerifyPassword.insets = new Insets(0, 0, 0, 5);
        gbc_lblVerifyPassword.gridx = 1;
        gbc_lblVerifyPassword.gridy = 2;
        panel_2.add(lblVerifyPassword, gbc_lblVerifyPassword);
        lblVerifyPassword.setBounds(171, 234, 105, 25);

        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 0, 5);
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.gridx = 3;
        gbc_passwordField.gridy = 2;
        panel_2.add(passwordField, gbc_passwordField);
        passwordField.setBounds(287, 183, 185, 25);

        panel_3 = new JPanel();
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.anchor = GridBagConstraints.NORTH;
        gbc_panel_3.insets = new Insets(0, 0, 5, 0);
        gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_3.gridx = 0;
        gbc_panel_3.gridy = 3;
        panel.add(panel_3, gbc_panel_3);

        returnToLoginBtn = new JButton("Back");
        panel_3.add(returnToLoginBtn);
        returnToLoginBtn.setBounds(267, 292, 140, 29);

        createAccountBtn = new JButton("Create Account");
        panel_3.add(createAccountBtn);
        createAccountBtn.setBounds(267, 292, 140, 29);
        createAccountBtn.addActionListener(this);
        returnToLoginBtn.addActionListener(this);

    }

    void display() {
        System.out.println("Displaying createAccountPanel");
        frame.getContentPane().removeAll();
        this.initializePanel();
        frame.validate();
        frame.repaint();
    }

    public void success() {
        PanelControl.loginPanel.display();
    }

    public void failure() {
        this.display();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == createAccountBtn) {
            CreateAccountData createAccountData = new CreateAccountData(jtUsername.getText(), lblEnterPassword.getText());
            try {
                client.sendToServer(createAccountData);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (source == returnToLoginBtn) {
            PanelControl.initialPanel.display();
        }

    }

}
