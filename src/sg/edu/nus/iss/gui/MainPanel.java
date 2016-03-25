package sg.edu.nus.iss.gui;

import javax.swing.JPanel;

import sg.edu.nus.iss.store.Cart;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainPanel extends JPanel {

	private static final long serialVersionUID = -8820162333877980019L;
	private MemberPanel memberPanel;
	private ProductPanel productPanel;
	private CategoryPanel categoryPanel;
	private DiscountPanel discountPanel;
	private CartPanel cartPanel;
	private TransactionProductPanel transactionProductPanel;

	/**
	 * Create the panel.
	 */
	public MainPanel(StoreApplication manager) {
		setLayout(new GridLayout(0, 2, 80, 60));
		
		JButton btnMakeATransaction = new JButton("Make a Transaction");
		btnMakeATransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transactionProductPanel = new TransactionProductPanel(manager);
				cartPanel = new CartPanel(manager);
				removeAll();
				Panel p = new Panel();				//New change 
				p.setLayout(new GridLayout(0, 1));
				p.add(transactionProductPanel);
				p.add(cartPanel);
				add("Center",p);
				revalidate();
				repaint();
			}
		});
		add(btnMakeATransaction);
		
		JButton btnMemberRegistration = new JButton("Member Registration");
		btnMemberRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberPanel = new MemberPanel(manager);
				removeAll();
				setLayout(new BorderLayout());
				memberPanel.setVisible(true);
				add("Center", memberPanel);
				revalidate();
				repaint();
			}
		});
		add(btnMemberRegistration);
		
		JButton btnCategory = new JButton("Category");
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				categoryPanel = new CategoryPanel(manager);
				removeAll();
				setLayout(new BorderLayout());
				categoryPanel.setVisible(true);
				add("Center", categoryPanel);
				revalidate();
				repaint();
			}
		});
		add(btnCategory);
		
		JButton productBtn = new JButton("Product");
		productBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				productPanel = new ProductPanel(manager);
				removeAll();
				setLayout(new BorderLayout());
				productPanel.setVisible(true);
				add("Center", productPanel);
				revalidate();
				repaint();
			}
		});
		add(productBtn);
		
		JButton vendorBtn = new JButton("Vendor");
		vendorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(vendorBtn);
		
		JButton reportBtn = new JButton("Report");
		reportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(reportBtn);
		
		JButton btnDiscount = new JButton("Discount");
		btnDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				discountPanel = new DiscountPanel(manager);
				removeAll();
				setLayout(new BorderLayout());
				discountPanel.setVisible(true);
				add("Center", discountPanel);
				revalidate();
				repaint();
			}
		});
		add(btnDiscount);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		add(btnExit);

	}

	public void refreshCart() {
		// TODO Auto-generated method stub
		cartPanel.refresh();
		
	}
	
	public Cart getSelectedCartItem() {
		Cart selectedLineItem = cartPanel.getRemoveSelectedCartItem();
		return selectedLineItem;
	}

}
