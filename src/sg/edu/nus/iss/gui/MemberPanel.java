package sg.edu.nus.iss.gui;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.store.Member;

public class MemberPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoreApplication manager;
	private JScrollPane scroller;
	private JTable table;
	private static final String[] COLUMN_NAMES = {"Name", "ID", "Loyalty Points"};
	private AbstractTableModel productTableModel;
	private ArrayList<Member> members ;
	

	/**
	 * Create the panel.
	 */
	public MemberPanel(StoreApplication manager) {
		this.manager = manager;
		setLayout (new BorderLayout());
		members = manager.getMembers();
		add(createButtonPanel(), BorderLayout.EAST);
		add (new JLabel ("Products"), BorderLayout.NORTH);
		table = new JTable();	
		table.setModel(getTableModel());
		productTableModel.fireTableDataChanged();
		scroller = new JScrollPane(table); //scroller automatically puts the table header at the top
		table.setFillsViewportHeight(true); // true : table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space. 
		add(scroller, BorderLayout.CENTER);

	}
	private JPanel createButtonPanel () {

        JPanel p = new JPanel (new GridLayout (0, 1));

        JButton b = new JButton ("Add");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
               //AddMemberDialog d = new AddMemberDialog (manager);
                //d.pack();
                //d.setVisible (true);
            }
        });
        p.add (b);

        b = new JButton ("Remove");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                //manager.removeSelectedMember();
            }
        });
        p.add (b);
        
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		removeAll();
        		//manager.removeLoginPanel();
        		add("Center",manager.createMainPanel());
        		//manager.refreshLoginPanel();
        		revalidate();
        		repaint();
        	}
        });
        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", p);
        bp.add("South", backBtn);
        
        
        return bp;
    }
	
	public TableModel getTableModel() {
		if (productTableModel != null) 
			return productTableModel;
		else {
		productTableModel = new AbstractTableModel() {
			
			@Override
		    public String getColumnName(int column) {
	        return COLUMN_NAMES[column];
	        }

	        @Override
	        public int getRowCount() {
	            return members.size();
	        }

	        @Override
	        public int getColumnCount() {
	            return COLUMN_NAMES.length;
	        }

	        @Override
	        public Object getValueAt(int rowIndex, int columnIndex) {
	            Member member = members.get(rowIndex);
	            switch (columnIndex) {
	                case 0: return member.getCustomerName();
	                case 1: return member.getMemberID();
	                case 2: return member.getLoyaltyPoints();
	                default: return null;
	            }
	        }
		};
	 //productTableModel.fireTableCellUpdated(members.size(), COLUMN_NAMES.length);
		return productTableModel;
	}
	}
}
