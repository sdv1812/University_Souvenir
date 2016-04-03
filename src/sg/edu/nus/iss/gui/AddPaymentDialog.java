package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import sg.edu.nus.iss.store.Cart;
import sg.edu.nus.iss.utils.OkCancelDialog;

/**
 * @author Koushik Radhakrishnan - AddPaymentDialog - Validates Payment -
 *
 */
public class AddPaymentDialog extends OkCancelDialog  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoreApplication manager ;
	private ArrayList<Cart> cart;
	private JTextArea productDetails;
	private JTextField total,discount,redeemPoints;

	public AddPaymentDialog(StoreApplication manager, TransactionProductPanel transactionProductPanel){
		super(manager.getMainWindow(),"Payment Details");
		this.manager = manager;
		cart = transactionProductPanel.getSelectedProducts();
		Iterator<Cart> i = cart.iterator();
		while(i.hasNext()){
			Cart c = (Cart)i.next();
			productDetails.append(c.toString() +System.getProperty("line.separator") );
		}
		productDetails.setEditable(false);

		total.setText(manager.getTransactionTotal());
		total.setEditable(false);
		discount.setText(manager.getDiscount());
		redeemPoints.setText(manager.getLoyaltyPoints());
		
	}


	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("Product Details"));
		productDetails = new JTextArea ();
		p.add(productDetails);
		p.add(new JLabel("Total"));
		total = new JTextField(20);
		p.add(total);
		p.add(new JLabel("Discount"));
		discount = new JTextField(20);
		p.add(discount);
		p.add(new JLabel("Enter points to be redeemed"));
		redeemPoints = new JTextField(20);
		p.add(redeemPoints);
		return p;
	}


	
	@Override
	protected boolean performOkAction() {
			boolean paymentStatus = true;
		try{
			double transactiontotal = Double.parseDouble(total.getText());
			double discountValue  = Double.parseDouble(discount.getText());
			double redeemPointsValue = Double.parseDouble(redeemPoints.getText());
			if (redeemPointsValue>Double.parseDouble(manager.getLoyaltyPoints())) {
				JOptionPane.showMessageDialog(null, "Points redeemed cannot be greater than Points available", "Redeem Points Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			double discountedAmount= manager.makePayment(transactiontotal,discountValue,redeemPointsValue);
			summaryDescription(discountedAmount,transactiontotal,redeemPointsValue,cart);			
   	
		}catch(Exception n){
    		JOptionPane.showMessageDialog(null, "Invalid Inputs entered", "Invalid Inputs", JOptionPane.ERROR_MESSAGE);
    		n.printStackTrace();
    		paymentStatus = false;
    		return paymentStatus;

		}
		
		paymentStatus = true;
		return paymentStatus;
	}


	public  void summaryDescription(double discountedAmount, double transactionTotal,double redeemPointsValue,ArrayList<Cart> cart) {
			PaymentSummaryDialog paymentSummary = new PaymentSummaryDialog(manager,discountedAmount,transactionTotal,redeemPointsValue,cart);
			paymentSummary.pack();
			paymentSummary.setVisible(true);
	}
}
