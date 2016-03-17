package sg.edu.nus.iss.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class StoreWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7460404160082932891L;
	private LoginPanel loginPanel;
	private StoreApplication manager;


	/**
	 * Create the frame.
	 */
	public StoreWindow(String s, StoreApplication manager) {
		super(s);
		this.manager = manager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		this.setMinimumSize(new Dimension(600, 500));
		loginPanel = new LoginPanel(manager);
		 add ("Center", loginPanel);
	}

	public MainPanel createMainPanel() {
		// TODO Auto-generated method stub
		return (loginPanel.createMainPanel());
	}


}
