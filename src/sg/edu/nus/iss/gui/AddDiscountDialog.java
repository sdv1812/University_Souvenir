package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.exceptions.BadValueException;
import sg.edu.nus.iss.utils.OkCancelDialog;
import static sg.edu.nus.iss.utils.StoreConstants.*;

/**
 * Dialog to add both kind of discounts : Occasional and Member
 * @author Sanskar Deepak
 *
 */

public class AddDiscountDialog extends OkCancelDialog{
	private static final long serialVersionUID = 1L;
	private JTextField codeText	,descText , startDateText , periodText , percText;;
	JComboBox<String> discountCategory;
	private static final String[] options = {"Member Discount", "Occasional Discount"};
	private StoreApplication manager;
	private DiscountPanel dp;

	public AddDiscountDialog(StoreApplication manager, DiscountPanel dp) {
		super(manager.getMainWindow(), "Add Discount");
		this.manager = manager;
		this.dp = dp;
		DATE_FORMAT.setLenient(false);
	}
	

	@Override
	protected JPanel createFormPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		
		JLabel selectDiscount = new JLabel("Select type of discount");
		discountCategory = new JComboBox<String>(options);
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
		
		discountCategory.setSelectedIndex(0);
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
		
		// add action listener to discount category combo box
		discountCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dCategory = (String)discountCategory.getSelectedItem();
				if(dCategory.equals("Member Discount")){
					startDateText.setText("ALWAYS");
					periodText.setText("ALWAYS");
					startDateText.setEnabled(false); //Since start date is "ALWAYS"
					periodText.setEnabled(false); //Since start date is "ALWAYS"
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
	
	/**
	 * Implement performOkAction() to perform action on pressing OK 
	 */
	protected boolean performOkAction() { 
		boolean b = false;
		Date startDate = null;
		int period = 0;
		if(startDateText.getText().length()==0||periodText.getText().length()==0 || codeText.getText().length()==0 ||
				descText.getText().length()==0||percText.getText().length()==0){
			return false;
		}
		
		try {
			String dCategory = (String)discountCategory.getSelectedItem();
			
			if(dCategory.equals("Occasional Discount")){ //to check for parsing error for occasional discount
			startDate = DATE_FORMAT.parse(startDateText.getText()); 
			period = Integer.parseInt(periodText.getText());
			}
			Float.parseFloat(percText.getText());
		
		if (((String)discountCategory.getSelectedItem()).equals("Member Discount")) {
			b = manager.addDiscount(codeText.getText(), descText.getText(), Float.parseFloat(percText.getText()));
		} else if ((((String)discountCategory.getSelectedItem()).equals("Occasional Discount"))) {
			b = manager.addDiscount(codeText.getText(), descText.getText(), Float.parseFloat(percText.getText()), startDate, period);
		}
		dp.refresh();
		if(b==false){
			JOptionPane.showMessageDialog(this,
                    "Discount Code already exists !",
                    "Duplicate Discount",
                    JOptionPane.ERROR_MESSAGE);
		}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
                    "Please enter valid value!",
                    "Number Format Exception",
                    JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e){
			JOptionPane.showMessageDialog(this,
                    "Please enter valid date in given date format!",
                    "Date Format Exception",
                    JOptionPane.ERROR_MESSAGE);
		} catch (BadValueException e) {
			e.printStackTrace();
		}
		
		return b;
	}

}
