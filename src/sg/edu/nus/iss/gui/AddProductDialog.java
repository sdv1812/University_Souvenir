package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.nus.iss.ft6.domain.Category;

public class AddProductDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoreApplication storeApplication;
	private JComboBox<String> categoryFiled;
	private JTextField productNameFiled;
	private JTextField productDescriptionFiled;
	private JTextField productQuantityFiled;
	private JTextField productPriceFiled;
	private JTextField productBarcodeNumberFiled;
	private JTextField productThresholdFiled;
	private JTextField productOrderQuantityFiled;
	private WindowListener windowListener=new WindowAdapter() {
		public void windowClosing(WindowEvent e){
			destroyDialog();
		}
	};
	
	public AddProductDialog(StoreApplication storeApplication){
		super(storeApplication.getMainWindow(),"Add new product"); //modified sanskar
		this.storeApplication=storeApplication;
		
		setLayout(new BorderLayout());
		add("Center",createFormPanel());
		add("South",createButtonPanel());
	}
	
	private JPanel createFormPanel(){
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(0, 2));
		
		p.add(new JLabel("Category"));
		categoryFiled=new JComboBox<>();
		for(Category c:storeApplication.getStore().getCategoryReg().getCategories()){
			categoryFiled.addItem(c.getName());
		}
		p.add(categoryFiled);
		
		p.add(new JLabel("product name"));
		productNameFiled=new JTextField(20);
		p.add(productNameFiled);
		
		p.add(new JLabel("product description"));
		productDescriptionFiled=new JTextField(20);
		p.add(productDescriptionFiled);
		
		p.add(new JLabel("product quantity"));
		productQuantityFiled=new JTextField(20);
		p.add(productQuantityFiled);
		
		p.add(new JLabel("product price"));
		productPriceFiled=new JTextField(20);
		p.add(productPriceFiled);
		
		p.add(new JLabel("product barcode number"));
		productBarcodeNumberFiled=new JTextField(20);
		p.add(productBarcodeNumberFiled);
		
		p.add(new JLabel("product threshold"));
		productThresholdFiled=new JTextField(20);
		p.add(productThresholdFiled);
		
		p.add(new JLabel("product order quantity"));
		productOrderQuantityFiled=new JTextField(20);
		p.add(productOrderQuantityFiled);
		
		p.setVisible(true);
		
		return p;
	}

	private JPanel createButtonPanel(){
		JPanel bp=new JPanel();
		
		JButton b = new JButton("Next");
		ActionListener l=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				performNextAction();
			}
		};
		b.addActionListener(l);
		bp.add(b);
		
		JButton cancelButton=new JButton("Cancel");
		ActionListener cl=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				destroyDialog();
			}
		};
		cancelButton.addActionListener(cl);
		bp.add(cancelButton);
		
		bp.setVisible(true);
		return bp;
	}
	
	protected boolean performNextAction(){
		int categoryIndex = categoryFiled.getSelectedIndex();
		Category category=storeApplication.getCategories().get(categoryIndex);
		String productName=productNameFiled.getText();
		String productDescription=productDescriptionFiled.getText();
		int productQuantity=Integer.parseInt(productQuantityFiled.getText());
		Double productPrice=Double.parseDouble(productPriceFiled.getText());
		String productBarcodeNumber=productBarcodeNumberFiled.getText();
		int productthreshold=Integer.parseInt(productThresholdFiled.getText());
		int productOrderNumber=Integer.parseInt(productOrderQuantityFiled.getText());
		
		storeApplication.getStore().getProductReg().addProduct(category, productName, productDescription, productQuantity,
				productPrice, productBarcodeNumber, productthreshold, productOrderNumber);
		
		productNameFiled.setText("");
		productDescriptionFiled.setText("");
		productQuantityFiled.setText("");
		productPriceFiled.setText("");
		productBarcodeNumberFiled.setText("");
		productThresholdFiled.setText("");
		productOrderQuantityFiled.setText("");
		
		storeApplication.getStoreWindow().refresh();
		
		return true;
	}
	
	public void destroyDialog(){
		setVisible(false);
		dispose();
	}
	
}
