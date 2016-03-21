package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.store.*;

public class AddPaymentDialog extends OkCancelDialog {
	private StoreApplication manager ;
	private java.util.ArrayList<Cart> cart;
	private JLabel productDetails;
	private JTextField total;
	private JTextField discount;
	private JTextField redeemPoints;
	private JTextField amountReceived;
	
	
	
	public AddPaymentDialog(StoreApplication manager){
		super(manager.getTransactionWindow(),"Payment Details");
		this.manager = manager;
		TransactionWindow transactionWindow = manager.getTransactionWindow();
		cart = transactionWindow.getCartItems();
		Iterator i = cart.iterator();
		while(i.hasNext()){
			Cart c = (Cart)i.next();
			productDetails.setText(c.toString());   // need to check its overrided or not . 
		}
		total.setText(manager.getTransactionTotal());
		discount.setText(manager.getDiscount());
		
	}


	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("Product Details"));
		productDetails = new JLabel();
		p.add(productDetails);
		p.add(new JLabel("Total"));
		total = new JTextField(20);
		p.add(total);
		p.add(new JLabel("Discount"));
		discount = new JTextField(20);
		p.add(discount);
		p.add(new JLabel("Redeem points"));
		redeemPoints = new JTextField(20);
		p.add(redeemPoints);
		p.add(new JLabel("Amount Received "));
		amountReceived = new JTextField(20);
		p.add(amountReceived);
		return p;
	}


	@Override
	protected boolean performOkAction() {
		//String makePayment(double amountReceived,double transActionTotal,double discount,double redeemPoints,ArrayList<Cart> c1 )
		double amountreceived = Double.parseDouble(amountReceived.getText());
		double transactiontotal = Double.parseDouble(total.getText());
		double discountValue  = Double.parseDouble(discount.getText());
		double redeemPointsValue = Double.parseDouble(redeemPoints.getText());
		manager.makePayment(amountreceived,transactiontotal,discountValue,redeemPointsValue,cart);
		return false;
	}
}
