package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.store.ReceiptPrinter;
import sg.edu.nus.iss.store.Transaction;
import sg.edu.nus.iss.utils.OkCancelDialog;

/**
 * @author koushik - Displays Payment Summary Details
 *
 */
public class PaymentSummaryDialog extends OkCancelDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField transactionTotalAmount;
	private JTextField discountedAmount;
	private JTextField receivedAmount;
	private JTextField balance;
	private JTextField pointsEarned;
	private StoreApplication manager;

	public PaymentSummaryDialog(StoreApplication manager, double transactionTotal, double amountAfterDiscount,
			double amountReceived, double balanceAmount, double earnedPoints) {
		super(manager.getMainWindow(), "Summary Details");
		this.manager = manager;
		transactionTotalAmount.setText(Double.toString(transactionTotal));
		transactionTotalAmount.setEditable(false);
		discountedAmount.setText(Double.toString(amountAfterDiscount));
		discountedAmount.setEditable(false);
		receivedAmount.setText(Double.toString(amountReceived));
		receivedAmount.setEditable(false);
		balance.setText(Double.toString(balanceAmount));
		balance.setEditable(false);
		pointsEarned.setText(Double.toString(earnedPoints));
		pointsEarned.setEditable(false);
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 2));
		p.add(new JLabel("Total"));
		transactionTotalAmount = new JTextField(20);
		p.add(transactionTotalAmount);
		p.add(new JLabel("Total After Discount"));
		discountedAmount = new JTextField(20);
		p.add(discountedAmount);
		p.add(new JLabel("Received Amount"));
		receivedAmount = new JTextField(20);
		p.add(receivedAmount);
		p.add(new JLabel("balance"));
		balance = new JTextField(20);
		p.add(balance);
		p.add(new JLabel("Earned Points"));
		pointsEarned = new JTextField(20);
		p.add(pointsEarned);
		return p;
	}

	@Override
	protected boolean performOkAction() {
        String[] options = { "Print Receipt", "cancel" };
        int option = JOptionPane.showOptionDialog(this, "Transaction Completed", "Transaction Completed",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        
        if(option == 0){
        	Transaction transaction = manager.getTransaction();
        	StringBuffer string = new StringBuffer("**************  Transaction Details************\n\n") ;
        	string.append("Transaction ID:" +transaction.getTranasctionId() +"\n\n");
        	string.append("	Transaction Amount: "+transactionTotalAmount.getText() + "\n\n");
        	if(transaction.getMemberId() != null) {
        		string.append("	Discounted Amount: "+discountedAmount.getText() + "\n\n");
        		string.append("	Earned points:"+pointsEarned.getText() + "\n\n");
        		string.append("	Points remaining: " +manager.getMember(transaction.getMemberId()).getLoyaltyPoints() +"\n\n");
        	}
        	string.append("	Received Amount: "+receivedAmount.getText() + "\n\n");
        	string.append("	Balance: "+balance.getText() + "\n\n");
        		
        	new ReceiptPrinter().print(string.toString());
        }
		return true;
	}

}
