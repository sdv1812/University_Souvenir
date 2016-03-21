package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import sg.edu.nus.iss.store.*;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	StoreApplication manager;
	private java.util.List<Product> product;
	private java.awt.List          productList;
	private JTextField 				memberId;
	private JTextField				quantity;
	private JTextField				productId;

	public ProductPanel(StoreApplication manager){
		this.manager = manager;
        /*setLayout (new BorderLayout());
        productList = new java.awt.List (5);
        productList.setMultipleMode (false);
        add ("North", new JLabel ("Products"));
        add ("Center", productList);*/
        productId = new JTextField(10);
        quantity = new JTextField(10);
        memberId =  new JTextField(10);
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        p.add(new JLabel("ProductId"));
        p.add(productId);
        p.add(new JLabel("QTY"));
        p.add(quantity);
        p.add(new JLabel("Member id "));
        p.add(memberId);
        add("North",p);
        add("Center",productList);
        add("East",createButtonPanel());
        
	}
	private JPanel createButtonPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout (0, 1));
		JButton b;
        ActionListener l;

        b = new JButton ("Add Products");
        l = new ActionListener () {
            public void actionPerformed (ActionEvent e) {
               // manager.makeBooking();
            	String productIdentity = productId.getText();
            	int  Quantity = Integer.parseInt(quantity.getText());
            	String memberIdentity = memberId.getText();
            	manager.addProductsToCart(productIdentity, Quantity, memberIdentity);
            	/*AddProductToCart addProductsToCart = new AddProductToCart();
            	addProductsToCart.addProductsToCart(productIdentity,Quantity , memberIdentity);*/
            	
            }
        };
        b.addActionListener (l);
        p.add (b);
		return null;
	}
	public void refresh(){
		/*product = manager.getProducts();
		System.out.println("Inside product panel"+product.size());
		Iterator<Product> i = product.iterator();
		while(i.hasNext()){
			productList.add(i.next().toString());
			System.out.println("Product list is "+productList.toString());
		}*/
	}
	
}
