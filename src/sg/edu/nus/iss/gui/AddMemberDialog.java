package sg.edu.nus.iss.gui;
import javax.swing.*;
import sg.edu.nus.iss.utils.*;
import java.awt.*;
import sg.edu.nus.iss.utils.ConfirmDialog;

public class  AddMemberDialog extends OkCancelDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel nameL;
	private JLabel cardNumberL;
	private JTextField nameT;
	private JTextField cardNumberT;
	
	
	private StoreApplication manager;
	private MemberPanel mp;

	public AddMemberDialog(StoreApplication manager, MemberPanel mp) {
		super(manager.getMainWindow(), "New Member Details");
		this.manager = manager;
		this.mp = mp;
	}
	protected JPanel createFormPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,0));
		
		nameL = new JLabel("Name");
		cardNumberL = new JLabel("Student/Staff ID");
		nameT = new JTextField(20);
		cardNumberT = new JTextField(20);
		
		panel.add(nameL);
		panel.add(nameT);
		panel.add(cardNumberL);
		panel.add(cardNumberT);
		
		return panel;

	}	
	
	protected boolean performOkAction() { //NEED TO CHECK WHILE ADDING IF MEMBER ALREADY EXIST
		if(nameT.getText().length()==0||cardNumberT.getText().length()==0){
			return false;
		}
		boolean b = manager.addMember(nameT.getText(), cardNumberT.getText());
		mp.refresh();
		if(b==false){
		 String title = "Duplicate Member";
	        String msg = "Member already exists !";
	        ConfirmDialog d = new ConfirmDialog (manager.getMainWindow(), title, msg) {

				private static final long serialVersionUID = 1L;

				protected boolean performOkAction () {
	                return true;
	            }
	        };
	        d.pack();
	        d.setVisible (true);
		}
		return b;
		
		
	}
	
}
