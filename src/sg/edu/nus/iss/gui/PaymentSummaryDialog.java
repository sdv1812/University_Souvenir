package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.store.Cart;
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
	private JTextField discountedAmount_text;
	private JTextField receivedAmount;
	private JTextField balance_text;
	private double balance,redeemPointsValue; 
	private StoreApplication manager;
	private ArrayList<Cart> cart;
	private JLabel balanceLabel;

	public PaymentSummaryDialog(StoreApplication manager, double discountedAmount ,double transactionTotal, double redeemPointsValue, ArrayList<Cart> cart) {
		super(manager.getMainWindow(), "Summary Details");
		this.manager = manager;
		this.cart = cart;
		this.redeemPointsValue = redeemPointsValue;
		transactionTotalAmount.setText(Double.toString(transactionTotal));
		transactionTotalAmount.setEditable(false);
		discountedAmount_text.setText(String.valueOf(discountedAmount));
		discountedAmount_text.setEditable(false);
		balance_text.setText(String.valueOf(discountedAmount));
		balance_text.setEditable(false);
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 2));
		p.add(new JLabel("Total"));
		transactionTotalAmount = new JTextField(20);
		p.add(transactionTotalAmount);
		p.add(new JLabel("Total After Discount"));
		discountedAmount_text = new JTextField(20);
		p.add(discountedAmount_text);
		p.add(new JLabel("Received Amount"));
		receivedAmount = new JTextField(20);
		receivedAmount.setText("0.0");
		receivedAmount.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				balance = manager.calculateBalance(Double.parseDouble(receivedAmount.getText()),Double.parseDouble(discountedAmount_text.getText()));
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		balanceLabel = new JLabel("Balance");
		p.add(receivedAmount);
		p.add(balanceLabel);
		balance_text = new JTextField(20);
		p.add(balance_text);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		manager.saveTransaction(redeemPointsValue,cart);
        String[] options = { "Print Receipt", "cancel" };
        if(balance == -1) {JOptionPane.showInputDialog(this, "Received amount can not be less thn total amount", "Transaction Failed",
                JOptionPane.ERROR_MESSAGE);
        
        return false;
        }
        else{
        int option = JOptionPane.showOptionDialog(this,"Change to be returned : $" +balance, "Transaction Completed",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
     	Transaction transaction = manager.getTransaction();
        if(option == 0){
  
        	StringBuffer string = new StringBuffer("**************  Transaction Details************\n\n") ;
        	string.append("Transaction ID:" +transaction.getTranasctionId() +"\n\n");
        	string.append("	Transaction Amount: $"+transactionTotalAmount.getText() + "\n\n");
        	if(transaction.getMemberId() != null) {
        		string.append("	Discounted Amount: $"+discountedAmount_text.getText() + "\n\n");
        		string.append("	Earned points: "+ manager.getBonusPoints() + "\n\n");
        		string.append("	Points remaining: " +manager.getMember(transaction.getMemberId()).getLoyaltyPoints() +"\n\n");
        	}
        	string.append("	Received Amount: $"+receivedAmount.getText() + "\n\n");
        	string.append("	Change returned: $"+balance + "\n\n");
        		
        	new ReceiptPrinter().print(string.toString());
        }
    	transaction.setTranasctionId(transaction.getTranasctionId()+1);
		return true;
	}
	}

}
