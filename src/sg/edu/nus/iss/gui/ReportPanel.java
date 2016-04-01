package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;

public class ReportPanel extends JPanel {
	private StoreApplication manager;
	private AbstractTableModel categoryTableModel;
	private AbstractTableModel productTableModel;
	private AbstractTableModel memberTableModel;
	private JTable table;
	private JScrollPane scroller;
	private Border raisedetched;
	private Border loweredetched;
	private String action_source;
	private JPanel cards;
	private static final String Category_ ="Category";
	private static final String Member_ ="Member";
	private static final String Product_ ="Product";
	private static final String Transaction_ ="Transaction";
	private java.awt.List productList;
	private AbstractTableModel transactionTable;



	/**
	 * Create the panel.
	 */
	public ReportPanel(StoreApplication manager) {
		this.manager = manager;
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED); 
		categoryTableModel = manager.getCategoryTableModel(); 
		productTableModel = manager.getProductTableModel();
		memberTableModel = manager.getMemberTableModel();
		setLayout (new BorderLayout());		
		cards = new JPanel(new CardLayout());
		JPanel transactionPanel = new JPanel();
		productList = manager.getTransactions(fromDate, toDate)
		transactionPanel.add("Center", productList);
		cards.add(createReportViewPanel(categoryTableModel, "List of All Categories"), Category_);
		cards.add(createReportViewPanel(memberTableModel, "List of All Members"), Member_);
		cards.add(createReportViewPanel(productTableModel, "List of All Products"),Product_);
		cards.add(transactionPanel, Transaction_);

		add(cards,BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.EAST);
		
	}
	
	public JPanel createButtonPanel () {
		JPanel p = new JPanel(new GridLayout(0,1,0,10));
		JPanel panel = new JPanel(new BorderLayout());
		CardLayout cl = (CardLayout)(cards.getLayout());
		JButton backBtn = new JButton("Back");

		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_source = (((JButton)e.getSource()).getText());
				refresh();
			}
		});

		p.add(backBtn);
		
		JButton catBtn = new JButton("Category");
		catBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_source = (((JButton)e.getSource()).getText());
				 cl.show(cards, Category_);
			}
		});
		
		p.add(catBtn);

		JButton memBtn = new JButton("Member");
		memBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_source = (((JButton)e.getSource()).getText());				 
				 cl.show(cards, Member_);
			}
		});
		
		p.add(memBtn);
		JButton prodBtn = new JButton("Product");
		prodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_source = (((JButton)e.getSource()).getText());
				cl.show(cards, Product_);
			}
		});
		
		p.add(prodBtn);
		
		JButton transBtn = new JButton("Transaction");
		transBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_source = (((JButton)e.getSource()).getText());
				cl.show(cards, Transaction_);
			}
		});
		
		p.add(transBtn);
		
		panel.add(p, "North");
		panel.setBorder(BorderFactory.createCompoundBorder(
				raisedetched, loweredetched)); 

		return panel;
	}
	
	public JPanel createReportViewPanel (AbstractTableModel tableModel, String title) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel label = new JLabel(title);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		table = new JTable();	
		table.setModel(tableModel);
		tableModel.fireTableDataChanged();
		scroller = new JScrollPane(table); //scroller automatically puts the table header at the top
		table.setFillsViewportHeight(true); // true : table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space. 

		panel.add(label, "North");
		panel.add(scroller, "Center");

		panel.setBorder(BorderFactory.createCompoundBorder(
				raisedetched, loweredetched)); 

		return panel;
	}
	
	public void refresh(){   
		if(action_source.equalsIgnoreCase("Back")){
			removeAll();
			add("Center",manager.createMainPanel());
			revalidate();
			repaint();
		} 
	}
	
	private JPanel showTransactionTable() {  
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel label = new JLabel("Vendors : ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		table = new JTable();	
		table.setModel(transactionTableModel);
		transactionTableModel.fireTableDataChanged();
		scroller = new JScrollPane(table); //scroller automatically puts the table header at the top
		table.setFillsViewportHeight(true); // true : table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space. 

		panel.add(label, "North");
		panel.add(scroller, "Center");

		panel.setBorder(BorderFactory.createCompoundBorder(
				raisedetched, loweredetched)); 

		return panel;

	}

}
	