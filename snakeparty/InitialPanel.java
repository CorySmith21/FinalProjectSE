package snakeparty;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InitialPanel implements ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private JButton btnLogin;
    private JButton btnCreateAccount;
    private LoginPanel loginpanel;
    private CreateAccountPanel createaccountpanel;

    public InitialPanel(JFrame frame) {
        this.frame = frame;
        this.initializePanel();
        
    }

    public void initializePanel() {
        frame.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 79, 479, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 84, 99, 29, 29, 0 };
        gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel = new JLabel("Snake Party");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Papyrus", Font.ITALIC, 32));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        panel.add(lblNewLabel, gbc_lblNewLabel);
        btnLogin = new JButton("Login");
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.fill = GridBagConstraints.VERTICAL;
        gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
        gbc_btnLogin.gridx = 1;
        gbc_btnLogin.gridy = 3;
        btnLogin.addActionListener(this) ;
        panel.add(btnLogin, gbc_btnLogin);

        btnCreateAccount = new JButton("Create Account");
        GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
        gbc_btnCreateAccount.insets = new Insets(0, 0, 0, 5);
        gbc_btnCreateAccount.fill = GridBagConstraints.VERTICAL;
        gbc_btnCreateAccount.gridx = 1;
        gbc_btnCreateAccount.gridy = 4;
        btnCreateAccount.addActionListener(this);
        panel.add(btnCreateAccount, gbc_btnCreateAccount);

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnLogin) {
            System.out.println("Login");
            loginpanel = new LoginPanel();
            loginpanel.setVisible(true);
            loginpanel.setSize(469,420);
           
            
        }
        
        if (source == btnCreateAccount) {
            System.out.println("Create");
            createaccountpanel = new CreateAccountPanel();
            createaccountpanel.setVisible(true);
            createaccountpanel.setSize(600,420);
        }
            
    }
}


