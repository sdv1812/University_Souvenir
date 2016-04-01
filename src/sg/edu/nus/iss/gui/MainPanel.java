package sg.edu.nus.iss.gui;

import javax.swing.JPanel;
import sg.edu.nus.iss.store.Cart;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = -8820162333877980019L;
	private MemberPanel memberPanel;
	private ProductPanel productPanel;
	private CategoryPanel categoryPanel;
	private DiscountPanel discountPanel;
	private TransactionProductPanel transactionProductPanel;
	private VendorPanel vendorPanel;

	// add
	private StoreApplication manager;
	private CheckProductsBelowThrethold checekProductsPanel;
	private ReportPanel reportPanel;

	/**
	 * Create the panel.
	 */
	public MainPanel(StoreApplication manager) {
		// add
		this.manager = manager;
		productPanel = new ProductPanel(manager, this);
		memberPanel = new MemberPanel(manager);
		categoryPanel = new CategoryPanel(manager);
		vendorPanel = new VendorPanel(manager);
		reportPanel = new ReportPanel(manager);
		discountPanel = new DiscountPanel(manager);
		transactionProductPanel = new TransactionProductPanel(manager);
		
		setLayout(new GridLayout(0, 2, 80, 60));

		JButton btnMakeATransaction = new JButton("Make a Transaction");
		btnMakeATransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				setLayout(new GridLayout(0, 1));
				add(transactionProductPanel);
				revalidate();
				repaint();
			}
		});
		add(btnMakeATransaction);

		JButton btnMemberRegistration = new JButton("Member Registration");
		btnMemberRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

				// productPanel = new ProductPanel(manager);
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
				removeAll();
				setLayout(new BorderLayout());
				vendorPanel.setVisible(true);
				add("Center", vendorPanel);
				revalidate();
				repaint();
			}
		});
		add(vendorBtn);

		JButton reportBtn = new JButton("Report");
		reportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				removeAll();
				setLayout(new BorderLayout());
				reportPanel.setVisible(true);
				add("Center", reportPanel);
				revalidate();
				repaint();
			}
		});
		add(reportBtn);

		JButton btnDiscount = new JButton("Discount");
		btnDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		transactionProductPanel.refresh();
		
	}

	public Cart getSelectedCartItem() {
		Cart selectedLineItem = transactionProductPanel.getRemoveSelectedCartItem();
		return selectedLineItem;
	}

	// add ,the method will be used when store keeper check inventory.
	public void actionPerformedOfCheckProductsBelowThrethold() {
		checekProductsPanel = new CheckProductsBelowThrethold(manager);
		removeAll();
		setLayout(new BorderLayout());
		checekProductsPanel.setVisible(true);
		add("Center", checekProductsPanel);
		revalidate();
		repaint();
	}
}
