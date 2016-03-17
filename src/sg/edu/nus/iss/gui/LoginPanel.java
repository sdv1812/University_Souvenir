package sg.edu.nus.iss.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6316032716933825384L;
	private JTextField textField;
	private JTextField textField_1;
	private StoreApplication manager;

	MainPanel mp;

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
		
		textField_1 = new JTextField();
		textField_1.setBounds(272, 218, 118, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				mp = createMainPanel();
				removeAll();
				setLayout(new BorderLayout());
				add("Center", mp);
				revalidate();
				repaint();

			}
		});
		btnLogin.setBounds(183, 275, 89, 23);
		add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReset.setBounds(301, 275, 89, 23);
		add(btnReset);

	}
	
	public MainPanel createMainPanel() {
		return (new MainPanel(manager));
	}
}
