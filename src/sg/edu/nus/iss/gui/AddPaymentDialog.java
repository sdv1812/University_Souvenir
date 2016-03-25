package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sg.edu.nus.iss.store.Cart;
import sg.edu.nus.iss.store.Product;
import sg.edu.nus.iss.utils.OkCancelDialog;

public class AddPaymentDialog extends OkCancelDialog {
	private StoreApplication manager ;
	private java.util.ArrayList<Cart> cart;
	private   JTextArea  productDetails;
	private JTextField total;
	private JTextField discount;
	private JTextField redeemPoints;
	private JTextField amountReceived;
	private CartPanel cartPanel;
	
	
	
	public AddPaymentDialog(StoreApplication manager, CartPanel cartPanel){
		super(manager.getMainWindow(),"Payment Details");
		this.manager = manager;
//		TransactionWindow transactionWindow = manager.getTransactionWindow();
		cart = cartPanel.getSelectedProducts();
		System.out.println("Add items to cart dialog array list size is"+cart.size());
		Iterator<Cart> i = cart.iterator();
		while(i.hasNext()){
			Cart c = (Cart)i.next();
			productDetails.append(c.toString() +System.getProperty("line.separator") );
		}
		productDetails.setEditable(false);

		total.setText(manager.getTransactionTotal());
		discount.setText(manager.getDiscount());
		
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
		if(total.getText().equals("")||amountReceived.getText().equals("")){
    		JOptionPane.showMessageDialog(null, "InvalidDetails", "Enter All details", JOptionPane.ERROR_MESSAGE);
    		return false ;
    		}
		double amountreceived = Double.parseDouble(amountReceived.getText());
		double transactiontotal = Double.parseDouble(total.getText());
		double discountValue  = Double.parseDouble(discount.getText());
		double redeemPointsValue = Double.parseDouble(redeemPoints.getText());
		manager.makePayment(amountreceived,transactiontotal,discountValue,redeemPointsValue,cart);
		return true;
	}
}
