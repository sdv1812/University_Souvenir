package sg.edu.nus.iss.gui;

import sg.edu.nus.iss.store.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class TransactionProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	StoreApplication manager;
	private java.util.List<Product> product;
	private java.awt.List          productList;
	private JTextField 				memberId;
	private JTextField				quantity;
	private JTextField				productId;

	public TransactionProductPanel(StoreApplication manager){
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
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("ProductId"));
		p.add(productId);
		p.add(new JLabel("QTY"));
		p.add(quantity);
		p.add(new JLabel("Member id "));
		p.add(memberId);
		p.add(createButtonPanel());
		add("North",p);
		// add("Center",productList);
		//add("East",createButtonPanel());

	}
	private JPanel createButtonPanel() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JButton b;
		ActionListener l;

		b = new JButton ("Add Products");
		l = new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				String productIdentity="";
				int  Quantity=0;
				String memberIdentity="";
				// manager.makeBooking();
				try{
					productIdentity = productId.getText();
					Quantity = Integer.parseInt(quantity.getText());
					memberIdentity = memberId.getText();
				}
				catch(Exception exception){
					JOptionPane.showMessageDialog(null, "InvalidDetails", "Enter valid format", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(productIdentity.length()==0||Quantity<=0){
					JOptionPane.showMessageDialog(null, "InvalidDetails", "InvalidDetails", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean addProductStatus = manager.addProductsToCart(manager.getProductByID(productIdentity), Quantity, manager.getMember(memberIdentity));
				if(!addProductStatus){
					JOptionPane.showMessageDialog(null, "Invalid Product detail", "No Product found", JOptionPane.ERROR_MESSAGE);
				}
				productId.setText("");
				quantity.setText("");

				//System.out.println("Add status in final is  "+addStatus);
				/*AddProductToCart addProductsToCart = new AddProductToCart();
        	addProductsToCart.addProductsToCart(productIdentity,Quantity , memberIdentity);*/
				/*if(addStatus==false){
        		JOptionPane.showMessageDialog (null, "Invalid details ", "Product Addition Window", JOptionPane.ERROR_MESSAGE);

        }}*/
			}};
			b.addActionListener (l);
			p.add (b, BorderLayout.NORTH);
			return p;

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
