package snakeparty;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;

import ocsf.client.AbstractClient;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InitialPanel implements ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private AbstractClient client;
    private JButton btnLogin;
    private JButton btnCreateAccount;
    private LoginPanel loginPanel;
    private CreateAccountPanel createAccountPannel;

    public InitialPanel(JFrame frame, AbstractClient client) {
        this.frame = frame;
        this.client = client;
        this.initializePanel();
    }

    public void initializePanel() {
        frame.getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 479, 0, 0 };
        gbl_panel.rowHeights = new int[] { 0, 84, 0, 0, 29, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel lblNewLabel = new JLabel("Snake Party!");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Papyrus", Font.ITALIC, 32));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        panel.add(lblNewLabel, gbc_lblNewLabel);
        btnLogin = new JButton("Login");
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
        gbc_btnLogin.gridx = 0;
        gbc_btnLogin.gridy = 2;
        panel.add(btnLogin, gbc_btnLogin);
        btnLogin.addActionListener(this) ;
        
                btnCreateAccount = new JButton("Create Account");
                GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
                gbc_btnCreateAccount.insets = new Insets(0, 0, 5, 5);
                gbc_btnCreateAccount.gridx = 0;
                gbc_btnCreateAccount.gridy = 3;
                panel.add(btnCreateAccount, gbc_btnCreateAccount);
                btnCreateAccount.addActionListener(this);
    }
    
   public void display() {
       frame.getContentPane().removeAll();
       frame.getContentPane().add(panel);
       frame.validate();
       frame.repaint();
   }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnLogin) {
            loginPanel = new LoginPanel(frame, client);
            loginPanel.display();
        }
        
        if (source == btnCreateAccount) {
            createAccountPannel = new CreateAccountPanel(frame, client);
            createAccountPannel.display();
        }  
    }
}


