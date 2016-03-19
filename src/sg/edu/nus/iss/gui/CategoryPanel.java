package sg.edu.nus.iss.gui;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import sg.edu.nus.iss.utils.ConfirmDialog;
import sg.edu.nus.iss.store.Category;

public class CategoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private StoreApplication manager;
	private JScrollPane scroller;
	private JTable table;
	private static final String[] COLUMN_NAMES = {"Code", "Category Name"};
	private AbstractTableModel productTableModel;
	private ArrayList<Category> categories ;
	private String action_source;
	private Border raisedetched;
	private Border loweredetched; 


	public CategoryPanel(StoreApplication manager) {
		this.manager = manager;

		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED); 

		setLayout (new BorderLayout());
		categories = manager.getCategories();
		add(createButtonPanel(), BorderLayout.EAST);
		add(showCategorListPanel(), BorderLayout.CENTER);
		add(createAddCategoryPanel(), BorderLayout.SOUTH);

	}
	private JPanel createAddCategoryPanel () { //To add category = Panel

		JPanel panel = new JPanel(new GridLayout(1, 0, 10, 0));

		//JPanel cp = new JPanel (new GridLayout (1, 0));

		JLabel label = new JLabel("Add a new Category : ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12 ));
		JLabel cName = new JLabel("Category Name: ");
		JTextField cNameT = new JTextField();
		JLabel cCode = new JLabel("Category Code: ");
		JTextField cCodeT = new JTextField();

		panel.add(label);
		panel.add(cName);
		panel.add(cNameT);
		panel.add(cCode);
		panel.add(cCodeT);

		JPanel bp = new JPanel();
		bp.setLayout(new GridLayout(1, 0));

		JButton addBtn = new JButton ("Add");
		addBtn.addActionListener (new ActionListener () { 
			public void actionPerformed (ActionEvent e) {
				action_source  =((JButton)e.getSource()).getText();
				if (cCodeT.getText()!=null && cNameT.getText()!=null && cCodeT.getText().length()>=3){
					if (!(manager.addCategory(cCodeT.getText(), cNameT.getText()))){
						JOptionPane.showMessageDialog(manager.getMainWindow(),
								"Category Code Already Exists !",
								"Duplicate Category Code",
								JOptionPane.INFORMATION_MESSAGE); 
					} else {
						refresh();
					}
				} else {
					JOptionPane.showMessageDialog(manager.getMainWindow(),
							"Category Code should have at least 3 characters",
							"Invalid Category Code",
							JOptionPane.ERROR_MESSAGE); 
				}
			}
		});

		JButton resetBtn = new JButton("Reset");

		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				action_source  =((JButton)e.getSource()).getText();
				cCodeT.setText(null);
				cNameT.setText(null);
			}
		});

		panel.add(addBtn);
		panel.add(resetBtn);

		panel.setBorder(BorderFactory.createCompoundBorder(
				raisedetched, loweredetched)); 

		return panel;
	}

	private JPanel createButtonPanel(){  //To add Buttons
		JPanel p = new JPanel(new GridLayout(0,1,0,10));
		JPanel panel = new JPanel(new BorderLayout());
		JButton backBtn = new JButton("Back");

		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_source = (((JButton)e.getSource()).getText());
				refresh();
			}
		});

		JButton removeBtn = new JButton ("Remove");
		removeBtn.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				String s =null;
				action_source  =(((JButton)e.getSource()).getText());
				if(table.getSelectedRow()!=-1){ //If at least one row is selected

					for(int i = 0 ; i<table.getColumnCount() ; i++)
					{
						if(table.getColumnName(i).equalsIgnoreCase("Code")){
							s = (String)table.getValueAt(table.getSelectedRow(), i);
							showConfirmDialog(s);
						}
					}
				}
			}
		});

		p.add(backBtn);
		p.add(removeBtn);

		panel.add(p, "North");
		panel.setBorder(BorderFactory.createCompoundBorder(
				raisedetched, loweredetched)); 

		return panel;

	}

	private JPanel showCategorListPanel() {  // To Show all Categories
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel label = new JLabel("Categories : ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		table = new JTable();	
		table.setModel(getTableModel());
		productTableModel.fireTableDataChanged();
		scroller = new JScrollPane(table); //scroller automatically puts the table header at the top
		table.setFillsViewportHeight(true); // true : table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space. 

		panel.add(label, "North");
		panel.add(scroller, "Center");

		panel.setBorder(BorderFactory.createCompoundBorder(
				raisedetched, loweredetched)); 

		return panel;

	}

	public TableModel getTableModel() {
		if (productTableModel != null) 
			return productTableModel;
		else {
			productTableModel = new AbstractTableModel() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public String getColumnName(int column) {
					return COLUMN_NAMES[column];
				}

				@Override
				public int getRowCount() {
					return categories.size();
				}

				@Override
				public int getColumnCount() {
					return COLUMN_NAMES.length;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					Category category = categories.get(rowIndex);
					switch (columnIndex) {
					case 0: return category.getCategoryCode();
					case 1: return category.getCategoryName();
					default: return null;
					}
				}
			};

			return productTableModel;
		}
	}
	public void refresh(){   //Reflect the changes done on the screen using buttons
		if(action_source.equalsIgnoreCase("Add")){
			int rowIndex = categories.size()-1;
			productTableModel.fireTableRowsInserted(rowIndex, rowIndex);
		}
		else if(action_source.equalsIgnoreCase("Remove")){
			productTableModel.fireTableRowsDeleted(table.getSelectedRow(), table.getSelectedRow());
		}

		else if(action_source.equalsIgnoreCase("Back")){
			removeAll();
			add("Center",manager.createMainPanel());
			revalidate();
			repaint();
		} 

	}

	public void showConfirmDialog(String s) { // Show the confirm dialog on removing and performs the remove functionality
		String title = "Remove Member";
		String msg = "Do you really want to remove member " + s + " ?";
		ConfirmDialog d = new ConfirmDialog (manager.getMainWindow(), title, msg) {

			private static final long serialVersionUID = 1L;

			protected boolean performOkAction () {
				manager.removeCategory(s);
				refresh();
				return true;
			}
		};
		d.setModal(true);
		d.setLocationRelativeTo(this);
		d.pack();
		d.setVisible (true);
	}

}
