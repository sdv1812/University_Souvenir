package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sg.edu.nus.iss.store.Product;

public class ProductPanel extends JPanel {
	private StoreApplication storeApplication;
	private List productList;
	private ArrayList<Product> products;
	
	public ProductPanel(StoreApplication storeApplication){
		this.storeApplication=storeApplication;
		setLayout(new BorderLayout());
		
		productList=new List(3);
		productList.setMultipleMode (false);
		add("North",new JLabel("products"));
		add("Center",productList);
		add("East",createButtonPanel());
		
	}
	
	public Panel createButtonPanel(){
		Panel p=new Panel();
		p.setLayout(new GridLayout(0, 1));
		JButton addButton=new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddProductDialog d=new AddProductDialog(storeApplication);
				d.pack();
				d.setVisible(true);
			}
		});
		JButton removeButton=new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				storeApplication.removeSelectedProduct();
				refresh();
			}
		});
		p.add(addButton);
		p.add(removeButton);
		return p;
	}
	
	public Product getSeclectedProduct(){
		int index=productList.getSelectedIndex();
		return (index==-1)?null:products.get(index);
	}
	
	public void refresh(){
		
		products=storeApplication.getStore().getProductReg().getProducts();
		
		productList.removeAll();
		for(Product p:products){
			productList.add(p.toString());
		}
		setVisible(true);
	}
}
