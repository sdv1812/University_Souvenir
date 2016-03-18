package sg.edu.nus.iss.gui;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class LoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6316032716933825384L;
	private JTextField textField;
	private JPasswordField textField_1;
	private StoreApplication manager;
	private MainPanel mp;
	

	/**
	 * Create the panel.
	 */
	public LoginPanel(StoreApplication manager) {
		this.manager = manager;
		setLayout(null);
		JLabel lblWelcomeToThe = new JLabel("Welcome To the Souvenir Store");
		lblWelcomeToThe.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblWelcomeToThe.setBounds(126, 11, 309, 32);
		add(lblWelcomeToThe);
		
		JLabel lblPleaseLogin = new JLabel("Please login to continue ...");
		lblPleaseLogin.setBounds(126, 162, 146, 14);
		add(lblPleaseLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(193, 197, 69, 14);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(193, 221, 62, 14);
		add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(272, 194, 118, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(272, 218, 118, 20);
		add(textField_1);
		textField_1.setColumns(10);
		

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(manager.validate(textField.getText(), textField_1.getText()/*(textField_1.getPassword()).toString()*/)){
					mp = createMainPanel();
					removeAll();
					setLayout(new BorderLayout());
					add("Center", mp);
					revalidate();
					repaint();		
				} else {
					LoginErrorPanel error = new LoginErrorPanel(manager);
					error.pack();
					error.setBounds(200, 200, 300, 100);
		            error.setVisible (true);
				}
				
			}
		});
		btnLogin.setBounds(183, 275, 89, 23);
		add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
			}
		});
		btnReset.setBounds(301, 275, 89, 23);
		add(btnReset);
		


	}
	
	public MainPanel createMainPanel() {
		return (new MainPanel(manager));
	}

	public void refresh() {
		// TODO Auto-generated method stub
		refresh();
	}
}
