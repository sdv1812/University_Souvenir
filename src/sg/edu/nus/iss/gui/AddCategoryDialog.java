package sg.edu.nus.iss.gui;
import javax.swing.*;
import sg.edu.nus.iss.utils.*;
import java.awt.*;

public class  AddCategoryDialog extends OkCancelDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel categoryCodeL;
	private JLabel categoryNameL;
	private JTextField categoryCodeT;
	private JTextField categoryNameT;
	
	
	private StoreApplication manager;
	private CategoryPanel cp;

	public AddCategoryDialog(StoreApplication manager, CategoryPanel cp) {
		super(manager.getMainWindow(), "New Member Details");
		this.manager = manager;
		this.cp = cp;
	}
	protected JPanel createFormPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,0));
		
		categoryNameL = new JLabel("Name");
		categoryCodeL = new JLabel("Student/Staff ID");
		categoryNameT = new JTextField(20);
		categoryCodeT = new JTextField(20);
		
		panel.add(categoryNameL);
		panel.add(categoryNameT);
		panel.add(categoryCodeL);
		panel.add(categoryCodeT);
		
		return panel;

	}	
	
	protected boolean performOkAction() { //NEED TO CHECK WHILE ADDING IF MEMBER ALREADY EXIST
		if(categoryNameT.getText().length()==0||categoryCodeT.getText().length()==0){
			return false;
		}
		boolean b = manager.addMember(categoryNameT.getText(), categoryCodeT.getText());
		mp.refresh();
		if(b==false){
			JOptionPane.showMessageDialog(this,
                    "Member already exists !",
                    "Duplicate Member",
                    JOptionPane.INFORMATION_MESSAGE);
		}
		return b;
		
		
	}
	
}
