package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import sg.edu.nus.iss.store.Category;
import sg.edu.nus.iss.store.Vendor;
import sg.edu.nus.iss.utils.OkCancelDialog;

public class AddVendorToCategoryDialog extends OkCancelDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8262197221667611250L;
	private VendorPanel vp;
	private JTable table;
	private AbstractTableModel categoryVendorTableModel;
	private static final String[] COLUMN_NAMES = {"Vendor Name", "Description"};
	private JScrollPane scroller;
	private Border raisedetched;
	private Border loweredetched; 
	private ArrayList<Vendor> vendorsPerCategory;
	
	public AddVendorToCategoryDialog(StoreApplication manager, VendorPanel vp) {
		super(manager.getMainWindow(), "Add Vendor TO Category");
		this.vp = vp;
		categoryVendorTableModel.fireTableDataChanged();
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED); 
		vendorsPerCategory = manager.getVendorsPerCategory(vp.getCategory(vp.getSelectedCategory()));
		for (Vendor v : vendorsPerCategory) {
			System.out.println(vp.getSelectedCategory());
		System.out.println("inside for loop dialog + "+v.getVendorName());
		}
	}
	
	protected JPanel createFormPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		//+vp.getSelectedCategory()
		JLabel label = new JLabel("Vendors Selling for Category ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		table = new JTable();	
		table.setModel(getTableModel());
		categoryVendorTableModel.fireTableDataChanged();
		scroller = new JScrollPane(table); //scroller automatically puts the table header at the top
		table.setFillsViewportHeight(true); // true : table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space. 

		panel.add(label, "North");
		panel.add(scroller, "Center");

		panel.setBorder(BorderFactory.createCompoundBorder(
				raisedetched, loweredetched)); 

		return panel;


	}	
	
	protected boolean performOkAction() { 
		return true;
	}
	
	public TableModel getTableModel() {
		if (categoryVendorTableModel != null) 
			return categoryVendorTableModel;
		else {
			categoryVendorTableModel = new AbstractTableModel() {

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
					return vendorsPerCategory.size();
				}

				@Override
				public int getColumnCount() {
					return COLUMN_NAMES.length;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					Vendor vendor = vendorsPerCategory.get(rowIndex);
					switch (columnIndex) {
					case 0: return vendor.getVendorName();
					case 1: return vendor.getDescription();
					default: return null;
					}
				}
			};

			return categoryVendorTableModel;
		}
	}

	
}