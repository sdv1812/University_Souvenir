package sg.edu.nus.iss.gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sg.edu.nus.iss.utils.*;

public class LoginErrorPanel extends OkCancelDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginErrorPanel(StoreApplication manager) {
		super(manager.getMainWindow(), "Login Error");
	}
	
	protected JPanel createFormPanel() {
		JPanel panel = new JPanel();
		JLabel errorMessage = new JLabel("Username or Password is incorrect !");
		panel.add(errorMessage);
		return panel;
	}	
	
	protected boolean performOkAction() { //NEED TO CHECK WHILE ADDING IF MEMBER ALREADY EXIST
		return true;
		
		
	}

}
