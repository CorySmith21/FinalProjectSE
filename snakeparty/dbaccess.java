package snakeparty;
import java.awt.BorderLayout;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;



import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class dbaccess extends JFrame {
	public dbaccess() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 558, 95);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Snake Party ");
		lblNewLabel.setFont(new Font("Savoye LET", Font.BOLD | Font.ITALIC, 50));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, Color.ORANGE, null, null));
		panel_1.setBounds(6, 113, 558, 382);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(33, 73, 133, 36);
		panel_1.add(lblNewLabel_1);
		
		jtUsername = new JTextField();
		jtUsername.setBounds(178, 82, 266, 27);
		panel_1.add(jtUsername);
		jtUsername.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(33, 144, 133, 36);
		panel_1.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(178, 153, 266, 27);
		panel_1.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(208, 229, 117, 29);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				
				try{
					
					Class.forName("com.mysql.jdbc.Driver"); 
						sqlConn = DriverManager.getConnection(dataConn,username,password);
						pst = sqlConn.prepareStatement("insert into snakeparty(username, password)value(?,?,?,?,?,?)");
						
						
						pst.setString(1, jtUsername.getText());
						pst.setString(2, passwordField.getText());
						
						
						pst.executeUpdate();
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Player Added");;
						upDateDB();
				
				
				
			}
				catch (ClassNotFoundException ex) {
				java.util.logging.Logger.getLogger(dbaccess.class.getName()).log(java.util.logging.Level.SEVERE,
						null, ex);
			
			}catch (SQLException ex) {
				java.util.logging.Logger.getLogger(dbaccess.class.getName()).log(java.util.logging.Level.SEVERE,
						null, ex);
			}
				}	
				
				
				
			
	
		});
		btnNewButton_1.setBounds(199, 260, 140, 29);
		panel_1.add(btnNewButton_1);
		
		JButton btnDeleteAcc = new JButton("Delete Account");
		btnDeleteAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				try 
				{
					
					
					deleteItem = JOptionPane.showConfirmDialog(null,"Confirm if you want to delete item",
							"Warning",JOptionPane.YES_NO_OPTION);
					if (deleteItem == JOptionPane.YES_OPTION) {
					
						Class.forName("com.mysql.jdbc.Driver"); 
						sqlConn = DriverManager.getConnection(dataConn,username,password);
						pst = sqlConn.prepareStatement("delete from snakeparty where Username =?");
						
						
						pst.setInt(1, id);
						pst.executeUpdate();
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Record updated");;
						upDateDB();
						
						jtUsername.setText("");
						passwordField.setText("");
						
				
				
				
			}}
				catch (ClassNotFoundException ex) {
				java.util.logging.Logger.getLogger(testdb.class.getName()).log(java.util.logging.Level.SEVERE,
						null, ex);
			
			}catch (SQLException ex) {
				System.err.println(ex);
				
						
			}
					}
				
				*/
				
				
			}
		});
		btnDeleteAcc.setBounds(199, 289, 140, 29);
		panel_1.add(btnDeleteAcc);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			private JFrame frame;	
			public void actionPerformed(ActionEvent e) {
			frame = new JFrame("Exit");
			if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "MySQL Connector", 
							JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
			System.exit(0);
			}
			}
			});
		btnNewButton_2.setBounds(208, 321, 117, 29);
		panel_1.add(btnNewButton_2);
	}
	

	private static final String username = "tester";
	private static final String password = "testerp1";
	private static final String dataConn = "jdbc:mysql://localhost:3306/snakeparty";
	
	Connection sqlConn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	int q, i, id, deleteItem;
	private JTextField jtUsername;
	private JPasswordField passwordField;
	@SuppressWarnings("unchecked")
	public void upDateDB(){

	try{
		
		Class.forName("com.mysql.jdbc.Driver"); 
			sqlConn = DriverManager.getConnection(dataConn,username,password);
			pst = sqlConn.prepareStatement("select * from snakeparty");
			
			rs = pst.executeQuery();
			ResultSetMetaData stData = rs.getMetaData();
			
			q = stData.getColumnCount();
			
					
					while(rs.next()) {
						Vector columnData = new Vector();
						
						
						for (i = 1; i <= q;i++) {
							columnData.add(rs.getString("Username"));
							columnData.add(rs.getString("Password"));
							
							
							
						
			}
					
								
						
			
				}
	


	}
	catch (Exception ex) {
		JOptionPane.showMessageDialog(null, ex);
	

	}}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    dbaccess frame = new dbaccess();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
};
