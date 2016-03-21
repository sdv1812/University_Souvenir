package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import sg.edu.nus.iss.store.*;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class cartPanel extends Panel {
	storeApplication manager;
	private java.util.ArrayList<Cart> cart;
	private java.awt.List         cartList;
	private CartDisplay c = new CartDisplay() ;
	public cartPanel(storeApplication manager){
		this.manager = manager;
		setLayout(new BorderLayout());
		cartList = new java.awt.List (6);
		cartList.setMultipleMode(true);
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		p.add(new JLabel("Cart Items"));
		add("North",p);
		add("Center",cartList);
		add("East",createButtonPanel());
	}
	

	public void refresh(){  // when to initiate this referesh?
		cartList.removeAll();
		TransactionWindow transActionWindow = manager.getTransactionWindow();
		// get Arraylist of cart objects and populate here in cartList
		cart = manager.getProductsAddedInCart();
		Iterator<Cart> i = cart.iterator();
		while(i.hasNext()){
			Cart c = (Cart) i.next();
			int quantity = c.getQuantity();
			Product product = c.getProduct();
			String productName = product.getProductName();
			double price = product.getPrice();
			CartDisplay cartDisplay = new  CartDisplay( productName ,  quantity ,  price);
			cartList.add(cartDisplay.toString());
		}
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		JButton cartButton;
		cartButton = new JButton("Add CheckedItems");
		ActionListener actionListener;
		actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent actionevent) {
						manager.beginCheckout(cart);
				
			}
		};
		cartButton.addActionListener(actionListener);
		panel.add(cartButton);
		
		cartButton = new JButton("Remove Items ");
		actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent actionevent) {
				// Transaction.addProductsToCart 
			}
		};
		cartButton.addActionListener(actionListener);
		panel.add(cartButton);
		
		JPanel buttonPanel = new  JPanel();
		
		
		return null;
	}


	public ArrayList<Cart> getSelectedProducts() {
				ArrayList<Cart> productList = new ArrayList<Cart>() ;
				int [] cartProducts = cartList.getSelectedIndexes();
				for(int i =0 ; i<cartProducts.length;i++){
						Cart c1 =cart.get(cartProducts[i]);
						productList.add(c1);
				}
		return productList;
	}
	public Cart getRemoveSelectedCartItem(){
				int idx = cartList.getSelectedIndex();
				return (idx == -1) ? null : cart.get(idx);
			
	}

}
