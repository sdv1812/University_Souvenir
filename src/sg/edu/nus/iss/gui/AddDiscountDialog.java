package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.utils.OkCancelDialog;

public class AddDiscountDialog extends OkCancelDialog{
	private static final long serialVersionUID = 1L;
	private JTextField codeText;
	private JTextField descText;
	private JTextField startDateText;
	private JTextField periodText;
	private JTextField percText;
	JComboBox discountCategory;
	private static final String[] options = {"Member Discount", "Occasional Discount"};
	private StoreApplication manager;
	private DiscountPanel dp;

	public AddDiscountDialog(StoreApplication manager, DiscountPanel dp, String title) {
		super(manager.getMainWindow(), title);
		this.manager = manager;
		this.dp = dp;
	}
	protected JPanel createFormPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		JLabel selectDiscount = new JLabel("Select type of discount");
		discountCategory = new JComboBox(options);
		JLabel dCode = new JLabel("Discount Code");
		codeText = new JTextField();
		JLabel dDescription = new JLabel("Description");
		descText = new JTextField();
		JLabel dStartDate = new JLabel("Start Date : (yyyy-mm-dd)");
		startDateText = new JTextField();
		JLabel dPeriod = new JLabel("Period");
		periodText = new JTextField();
		JLabel dPercent = new JLabel("Percentage");
		percText = new JTextField();
		
		panel.add(selectDiscount);
		panel.add(discountCategory);
		panel.add(dCode);
		panel.add(codeText);
		panel.add(dDescription);
		panel.add(descText);
		panel.add(dStartDate);
		panel.add(startDateText);
		panel.add(dPeriod);
		panel.add(periodText);
		panel.add(dPercent);
		panel.add(percText);
		
		codeText.setEnabled(false);
		descText.setEnabled(false);
		startDateText.setEnabled(false);
		periodText.setEnabled(false);
		percText.setEnabled(false);
		startDateText.setText("ALWAYS");
		periodText.setText("ALWAYS");		
		
		discountCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dCategory = (String)discountCategory.getSelectedItem();
				if(dCategory.equals("Member Discount")){
					startDateText.setText("ALWAYS");
					periodText.setText("ALWAYS");
					startDateText.setEnabled(false);
					periodText.setEnabled(false);
					codeText.setEnabled(true);
					descText.setEnabled(true);
					percText.setEnabled(true);
				}
				if(dCategory.equals("Occasional Discount")){
					startDateText.setText(null);
					periodText.setText(null);
					codeText.setEnabled(true);
					descText.setEnabled(true);
					startDateText.setEnabled(true);
					periodText.setEnabled(true);
					percText.setEnabled(true);
				}
			}
		});
		
		return panel;

	}	
	
	protected boolean performOkAction() { 
		boolean b = false;
		if(startDateText.getText().length()==0||periodText.getText().length()==0 || codeText.getText().length()==0 ||
				descText.getText().length()==0||percText.getText().length()==0){
			return false;
		}
		if(!isFloat(percText.getText())){
			JOptionPane.showMessageDialog(this,
                    "Percentage should be a number with no string",
                    "Invalid percentage",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
		
		if (((String)discountCategory.getSelectedItem()).equals("Member Discount")) {
			b = manager.addDiscount(codeText.getText(), descText.getText(), Float.parseFloat(percText.getText()), "", "");
		} else if ((((String)discountCategory.getSelectedItem()).equals("Occasional Discount"))) {
			b = manager.addDiscount(codeText.getText(), descText.getText(), Float.parseFloat(percText.getText()), startDateText.getText(), periodText.getText());
		}
		dp.refresh();
		if(b==false){
			JOptionPane.showMessageDialog(this,
                    "Discount Code already exists !",
                    "Duplicate Discount",
                    JOptionPane.ERROR_MESSAGE);
		}
		}
		return b;
	}
	
	public boolean isFloat( String input ) {
	    try {
	        Float.parseFloat( input );
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}

}
