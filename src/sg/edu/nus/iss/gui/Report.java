package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import sg.edu.nus.iss.store.Category;

public class Report extends JPanel {
	private StoreApplication manager;
	private AbstractTableModel categoryTableModel;
	private AbstractTableModel productTableModel;
	private AbstractTableModel memberTabelModel;
	private JTable table;
	private JScrollPane scroller;
	private Border raisedetched;
	private Border loweredetched;
	private String action_source;

	/**
	 * Create the panel.
	 */
	public Report(StoreApplication manager) {
		this.manager = manager;
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED); 
		categoryTableModel = manager.getCategoryTableModel(); 
		//productTableModel = manager.getProductTableModel();
		//memberTableModel = manager.getMemberTableModel();
	}
	
	public JPanel createButtonPanel () {
		JPanel p = new JPanel(new GridLayout(0,1,0,10));
		JPanel panel = new JPanel(new BorderLayout());
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
				createReportViewPanel(categoryTableModel, "List of All Categories");
				refresh();
			}
		});

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

	}

}
	